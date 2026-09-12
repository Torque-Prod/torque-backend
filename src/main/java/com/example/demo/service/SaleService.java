package com.example.demo.service;

import com.example.demo.dto.CreateSaleRequest;
import com.example.demo.dto.GridSearchDto;
import com.example.demo.dto.PagedSaleResponse;
import com.example.demo.dto.SaleDto;
import com.example.demo.entity.Part;
import com.example.demo.entity.Sale;
import com.example.demo.entity.SaleItem;
import com.example.demo.repository.PartRepository;
import com.example.demo.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final PartRepository partRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public SaleDto checkout(CreateSaleRequest request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cannot checkout an empty cart");
        }

        // Get currently authenticated user username
        String soldBy = "System";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getName() != null) {
            soldBy = auth.getName();
        }

        // 1. Calculate totals & validate parts / stock
        double subtotal = 0.0;
        List<SaleItem> saleItems = new ArrayList<>();

        for (CreateSaleRequest.SaleItemRequest itemReq : request.getItems()) {
            if (itemReq.getQuantity() == null || itemReq.getQuantity() <= 0) {
                throw new IllegalArgumentException("Invalid item quantity");
            }

            boolean isMenuitem = "MENU_ITEM".equalsIgnoreCase(itemReq.getItemType());

            SaleItem saleItem;

            if (isMenuitem) {
                // ── Restaurant menu item: no stock lookup, no deduction ──
                double unitPrice = (itemReq.getUnitPrice() != null && itemReq.getUnitPrice() >= 0)
                        ? itemReq.getUnitPrice() : 0.0;
                double lineTotal = unitPrice * itemReq.getQuantity();
                subtotal += lineTotal;

                saleItem = SaleItem.builder()
                        .partId(null)                                   // no part ID for food items
                        .partName(itemReq.getItemName() != null ? itemReq.getItemName() : "Menu Item")
                        .unitPrice(unitPrice)
                        .quantity(itemReq.getQuantity())
                        .totalPrice(lineTotal)
                        .build();
            } else {
                // ── Spare part: validate stock and deduct ──
                Part part = partRepository.findById(itemReq.getPartId())
                        .orElseThrow(() -> new IllegalArgumentException("Part not found: ID " + itemReq.getPartId()));

                if (part.getStockQuantity() < itemReq.getQuantity()) {
                    throw new IllegalStateException("Insufficient stock for: " + part.getName() +
                            " (Requested: " + itemReq.getQuantity() + ", Available: " + part.getStockQuantity() + ")");
                }

                // Deduct stock
                part.setStockQuantity(part.getStockQuantity() - itemReq.getQuantity());
                partRepository.save(part);

                double unitPrice = (itemReq.getUnitPrice() != null && itemReq.getUnitPrice() >= 0)
                        ? itemReq.getUnitPrice()
                        : part.getPrice();

                double lineTotal = unitPrice * itemReq.getQuantity();
                subtotal += lineTotal;

                saleItem = SaleItem.builder()
                        .partId(part.getId())
                        .partName(part.getName())
                        .unitPrice(unitPrice)
                        .quantity(itemReq.getQuantity())
                        .totalPrice(lineTotal)
                        .build();
            }

            saleItems.add(saleItem);
        }

        double discount = (request.getDiscount() != null && request.getDiscount() >= 0) ? request.getDiscount() : 0.0;
        double tax = (request.getTax() != null && request.getTax() >= 0) ? request.getTax() : 0.0;
        double netTotal = Math.max(0.0, subtotal - discount + tax);

        double paidAmount = (request.getPaidAmount() != null && request.getPaidAmount() >= 0)
                ? request.getPaidAmount()
                : netTotal;

        double changeAmount = Math.max(0.0, paidAmount - netTotal);

        // 2. Generate unique invoice number: INV-yyyyMMdd-XXXX
        String datePrefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long countToday = saleRepository.count() + 1;
        String invoiceNumber = String.format("INV-%s-%04d", datePrefix, countToday % 10000);

        String customerName = (request.getCustomerName() != null && !request.getCustomerName().trim().isEmpty())
                ? request.getCustomerName().trim()
                : "Walk-in Customer";

        String paymentMethod = (request.getPaymentMethod() != null && !request.getPaymentMethod().trim().isEmpty())
                ? request.getPaymentMethod().toUpperCase()
                : "CASH";

        // 3. Build & Save Sale
        Sale sale = Sale.builder()
                .invoiceNumber(invoiceNumber)
                .customerId(request.getCustomerId())
                .customerName(customerName)
                .customerPhone(request.getCustomerPhone())
                .paymentMethod(paymentMethod)
                .subtotal(subtotal)
                .discount(discount)
                .tax(tax)
                .netTotal(netTotal)
                .paidAmount(paidAmount)
                .changeAmount(changeAmount)
                .soldBy(soldBy)
                .notes(request.getNotes())
                .build();

        // Link parent reference
        for (SaleItem item : saleItems) {
            item.setSale(sale);
        }
        sale.setItems(saleItems);

        Sale saved = saleRepository.save(sale);
        return toDto(saved);
    }

    public List<SaleDto> getAllSales() {
        return saleRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public SaleDto getSaleById(Long id) {
        return saleRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found: ID " + id));
    }

    public SaleDto getSaleByInvoiceNumber(String invoiceNumber) {
        return saleRepository.findByInvoiceNumber(invoiceNumber)
                .map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found: " + invoiceNumber));
    }

    public PagedSaleResponse searchSales(GridSearchDto dto) {
        String baseFrom = "FROM sales s WHERE 1=1";
        StringBuilder where = new StringBuilder();
        List<Object> params = new ArrayList<>();

        if (dto.getGlobalFilter() != null && !dto.getGlobalFilter().isBlank()) {
            where.append(" AND (s.invoice_number LIKE ? OR s.customer_name LIKE ? OR s.customer_phone LIKE ?)");
            String term = "%" + dto.getGlobalFilter().trim() + "%";
            params.add(term);
            params.add(term);
            params.add(term);
        }

        if (dto.getStartDate() != null && !dto.getStartDate().isBlank()) {
            where.append(" AND s.created_at >= ?");
            params.add(dto.getStartDate() + " 00:00:00");
        }
        if (dto.getEndDate() != null && !dto.getEndDate().isBlank()) {
            where.append(" AND s.created_at <= ?");
            params.add(dto.getEndDate() + " 23:59:59");
        }

        if (dto.getInvoiceNumber() != null && !dto.getInvoiceNumber().isBlank()) {
            where.append(" AND s.invoice_number LIKE ?");
            params.add("%" + dto.getInvoiceNumber().trim() + "%");
        }
        if (dto.getCustomerName() != null && !dto.getCustomerName().isBlank()) {
            where.append(" AND s.customer_name LIKE ?");
            params.add("%" + dto.getCustomerName().trim() + "%");
        }
        if (dto.getPaymentMethod() != null && !dto.getPaymentMethod().isBlank()) {
            where.append(" AND s.payment_method = ?");
            params.add(dto.getPaymentMethod().trim());
        }

        // Count query
        String countSql = "SELECT COUNT(*) " + baseFrom + where;
        Query countQuery = entityManager.createNativeQuery(countSql);
        setParams(countQuery, params);
        long totalRecords = ((Number) countQuery.getSingleResult()).longValue();

        // Revenue sum query
        String revSql = "SELECT COALESCE(SUM(s.net_total), 0) " + baseFrom + where;
        Query revQuery = entityManager.createNativeQuery(revSql);
        setParams(revQuery, params);
        double totalRevenue = ((Number) revQuery.getSingleResult()).doubleValue();

        // Sort
        String sortClause = " ORDER BY s.created_at DESC";
        if (dto.getSortField() != null && !dto.getSortField().isBlank()) {
            String col = switch (dto.getSortField()) {
                case "invoiceNumber" -> "s.invoice_number";
                case "customerName"  -> "s.customer_name";
                case "netTotal"      -> "s.net_total";
                case "createdAt"     -> "s.created_at";
                default              -> "s.created_at";
            };
            String dir = (dto.getSortOrder() != null && dto.getSortOrder() == -1) ? "DESC" : "ASC";
            sortClause = " ORDER BY " + col + " " + dir;
        }

        // Data query
        String dataSql = "SELECT s.id " + baseFrom + where + sortClause;
        Query dataQuery = entityManager.createNativeQuery(dataSql);
        setParams(dataQuery, params);

        int first = (dto.getFirst() != null && dto.getFirst() >= 0) ? dto.getFirst() : 0;
        int rows  = (dto.getRows()  != null && dto.getRows()  > 0)  ? dto.getRows()  : 25;
        dataQuery.setFirstResult(first);
        dataQuery.setMaxResults(rows);

        @SuppressWarnings("unchecked")
        List<Object> idResults = dataQuery.getResultList();
        List<Long> ids = idResults.stream().map(o -> ((Number) o).longValue()).collect(Collectors.toList());

        List<SaleDto> data = ids.stream()
                .map(id -> saleRepository.findById(id).map(this::toDto).orElse(null))
                .filter(dtoItem -> dtoItem != null)
                .collect(Collectors.toList());

        return PagedSaleResponse.builder()
                .data(data)
                .totalRecords(totalRecords)
                .totalRevenue(totalRevenue)
                .build();
    }

    private SaleDto toDto(Sale sale) {
        List<SaleDto.SaleItemDto> items = (sale.getItems() != null)
                ? sale.getItems().stream().map(i -> SaleDto.SaleItemDto.builder()
                        .id(i.getId())
                        .partId(i.getPartId())
                        .partName(i.getPartName())
                        .unitPrice(i.getUnitPrice())
                        .quantity(i.getQuantity())
                        .totalPrice(i.getTotalPrice())
                        .build()).collect(Collectors.toList())
                : new ArrayList<>();

        return SaleDto.builder()
                .id(sale.getId())
                .invoiceNumber(sale.getInvoiceNumber())
                .customerId(sale.getCustomerId())
                .customerName(sale.getCustomerName())
                .customerPhone(sale.getCustomerPhone())
                .paymentMethod(sale.getPaymentMethod())
                .subtotal(sale.getSubtotal())
                .discount(sale.getDiscount())
                .tax(sale.getTax())
                .netTotal(sale.getNetTotal())
                .paidAmount(sale.getPaidAmount())
                .changeAmount(sale.getChangeAmount())
                .soldBy(sale.getSoldBy())
                .notes(sale.getNotes())
                .createdAt(sale.getCreatedAt())
                .items(items)
                .build();
    }

    private void setParams(Query query, List<Object> params) {
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i));
        }
    }
}
