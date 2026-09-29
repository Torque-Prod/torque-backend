package com.example.demo.service;

import com.example.demo.dto.CheckoutRequest;
import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.dto.CreateSaleRequest;
import com.example.demo.dto.RestaurantOrderDto;
import com.example.demo.dto.SaleDto;
import com.example.demo.entity.RestaurantMenuItem;
import com.example.demo.entity.RestaurantOrder;
import com.example.demo.entity.RestaurantOrderItem;
import com.example.demo.entity.TableSession;
import com.example.demo.entity.enums.OrderItemStatus;
import com.example.demo.entity.enums.OrderSource;
import com.example.demo.entity.enums.OrderStatus;
import com.example.demo.repository.RestaurantMenuItemRepository;
import com.example.demo.repository.RestaurantOrderItemRepository;
import com.example.demo.repository.RestaurantOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantOrderService {

    private final RestaurantOrderRepository orderRepository;
    private final RestaurantOrderItemRepository orderItemRepository;
    private final RestaurantMenuItemRepository menuItemRepository;
    private final TableSessionService sessionService;
    private final SaleService saleService;

    /**
     * Create a new order for a table (opens session automatically if table is currently available).
     */
    @Transactional
    public RestaurantOrderDto createOrder(CreateOrderRequest request, OrderSource source) {
        if (request.getTableId() == null) {
            throw new IllegalArgumentException("Table ID is required");
        }
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        // Get active session or open a new one
        TableSession session = sessionService.getOrOpenActiveSessionEntity(request.getTableId());

        // Generate Order Number: ORD-YYYYMMDD-XXXX
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        long todayCount = orderRepository.countOrdersSince(startOfDay) + 1;
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String orderNumber = String.format("ORD-%s-%04d", dateStr, todayCount);

        OrderStatus initialStatus = (source == OrderSource.CUSTOMER_QR) ? OrderStatus.PLACED : OrderStatus.CONFIRMED;

        RestaurantOrder order = RestaurantOrder.builder()
                .tableSession(session)
                .orderNumber(orderNumber)
                .source(source != null ? source : OrderSource.WAITER)
                .status(initialStatus)
                .notes(request.getNotes())
                .items(new ArrayList<>())
                .build();

        for (CreateOrderRequest.OrderItemRequest itemReq : request.getItems()) {
            if (itemReq.getMenuItemId() == null) {
                throw new IllegalArgumentException("Menu item ID is required");
            }
            int qty = (itemReq.getQuantity() != null && itemReq.getQuantity() > 0) ? itemReq.getQuantity() : 1;

            RestaurantMenuItem menuItem = menuItemRepository.findById(itemReq.getMenuItemId())
                    .orElseThrow(() -> new IllegalArgumentException("Menu item not found: " + itemReq.getMenuItemId()));

            RestaurantOrderItem orderItem = RestaurantOrderItem.builder()
                    .order(order)
                    .menuItemId(menuItem.getId())
                    .itemName(menuItem.getName())
                    .unitPrice(menuItem.getPrice())
                    .quantity(qty)
                    .notes(itemReq.getNotes())
                    .status(OrderItemStatus.PENDING)
                    .build();

            order.getItems().add(orderItem);
        }

        RestaurantOrder savedOrder = orderRepository.save(order);
        return mapToDto(savedOrder);
    }

    /**
     * Fetch all orders for a table session.
     */
    @Transactional(readOnly = true)
    public List<RestaurantOrderDto> getOrdersBySession(Long sessionId) {
        List<RestaurantOrder> orders = orderRepository.findAllByTableSessionIdOrderByCreatedAtAsc(sessionId);
        return orders.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Update individual item status (e.g. kitchen marking it PREPARING, READY, SERVED, or CANCELLED).
     */
    @Transactional
    public RestaurantOrderDto updateItemStatus(Long orderItemId, OrderItemStatus newStatus) {
        RestaurantOrderItem item = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new IllegalArgumentException("Order item not found: " + orderItemId));

        item.setStatus(newStatus);
        orderItemRepository.save(item);

        RestaurantOrder order = item.getOrder();

        // Check if all items in order have status READY/SERVED or CANCELLED to update order level status
        boolean allItemsDone = order.getItems().stream()
                .allMatch(i -> i.getStatus() == OrderItemStatus.SERVED || i.getStatus() == OrderItemStatus.CANCELLED);
        boolean allItemsReady = order.getItems().stream()
                .allMatch(i -> i.getStatus() == OrderItemStatus.READY || i.getStatus() == OrderItemStatus.SERVED || i.getStatus() == OrderItemStatus.CANCELLED);

        if (allItemsDone) {
            order.setStatus(OrderStatus.SERVED);
        } else if (allItemsReady) {
            order.setStatus(OrderStatus.READY);
        } else if (newStatus == OrderItemStatus.PREPARING) {
            order.setStatus(OrderStatus.IN_PREPARATION);
        }
        orderRepository.save(order);

        return mapToDto(order);
    }

    /**
     * Update order status.
     */
    @Transactional
    public RestaurantOrderDto updateOrderStatus(Long orderId, OrderStatus newStatus) {
        RestaurantOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
        order.setStatus(newStatus);

        // If order cancelled, mark pending items cancelled too
        if (newStatus == OrderStatus.CANCELLED) {
            for (RestaurantOrderItem item : order.getItems()) {
                if (item.getStatus() != OrderItemStatus.SERVED) {
                    item.setStatus(OrderItemStatus.CANCELLED);
                }
            }
        }

        RestaurantOrder saved = orderRepository.save(order);
        return mapToDto(saved);
    }

    /**
     * Checkout session — bridges restaurant orders to POS Sale system and closes the table session.
     */
    @Transactional
    public SaleDto checkoutSession(Long sessionId, CheckoutRequest checkoutReq) {
        TableSession session = sessionService.getSessionEntity(sessionId);
        List<RestaurantOrder> orders = orderRepository.findAllByTableSessionIdOrderByCreatedAtAsc(sessionId);

        // Aggregate non-cancelled items across all orders in session
        List<CreateSaleRequest.SaleItemRequest> saleItems = new ArrayList<>();
        for (RestaurantOrder order : orders) {
            if (order.getStatus() == OrderStatus.CANCELLED) continue;
            for (RestaurantOrderItem item : order.getItems()) {
                if (item.getStatus() == OrderItemStatus.CANCELLED) continue;

                saleItems.add(CreateSaleRequest.SaleItemRequest.builder()
                        .itemType("MENU_ITEM")
                        .itemName(item.getItemName())
                        .unitPrice(item.getUnitPrice())
                        .quantity(item.getQuantity())
                        .build());
            }
        }

        if (saleItems.isEmpty()) {
            throw new IllegalStateException("No active items to checkout for table " + session.getTableNumber());
        }

        String customerName = (checkoutReq != null && checkoutReq.getCustomerName() != null && !checkoutReq.getCustomerName().trim().isEmpty())
                ? checkoutReq.getCustomerName().trim()
                : "Table " + session.getTableNumber();

        CreateSaleRequest saleReq = CreateSaleRequest.builder()
                .customerId(checkoutReq != null ? checkoutReq.getCustomerId() : null)
                .customerName(customerName)
                .customerPhone(checkoutReq != null ? checkoutReq.getCustomerPhone() : null)
                .paymentMethod(checkoutReq != null && checkoutReq.getPaymentMethod() != null ? checkoutReq.getPaymentMethod() : "CASH")
                .discount(checkoutReq != null && checkoutReq.getDiscount() != null ? checkoutReq.getDiscount() : 0.0)
                .tax(checkoutReq != null && checkoutReq.getTax() != null ? checkoutReq.getTax() : 0.0)
                .paidAmount(checkoutReq != null ? checkoutReq.getPaidAmount() : null)
                .notes("Restaurant Table " + session.getTableNumber() + " session checkout")
                .items(saleItems)
                .build();

        // 1. Post to POS Sale system
        SaleDto saleDto = saleService.checkout(saleReq);

        // 2. Close session & set table status to NEEDS_CLEANING
        sessionService.closeSession(sessionId);

        return saleDto;
    }

    public RestaurantOrderDto mapToDto(RestaurantOrder order) {
        double itemTotal = 0.0;
        List<RestaurantOrderDto.ItemDto> itemDtos = new ArrayList<>();

        if (order.getItems() != null) {
            for (RestaurantOrderItem item : order.getItems()) {
                double lineTotal = item.getLineTotal();
                if (item.getStatus() != OrderItemStatus.CANCELLED) {
                    itemTotal += lineTotal;
                }
                itemDtos.add(RestaurantOrderDto.ItemDto.builder()
                        .id(item.getId())
                        .menuItemId(item.getMenuItemId())
                        .itemName(item.getItemName())
                        .unitPrice(item.getUnitPrice())
                        .quantity(item.getQuantity())
                        .notes(item.getNotes())
                        .status(item.getStatus())
                        .lineTotal(lineTotal)
                        .build());
            }
        }

        return RestaurantOrderDto.builder()
                .id(order.getId())
                .sessionId(order.getTableSession() != null ? order.getTableSession().getId() : null)
                .orderNumber(order.getOrderNumber())
                .source(order.getSource())
                .status(order.getStatus())
                .notes(order.getNotes())
                .items(itemDtos)
                .itemTotal(itemTotal)
                .createdAt(order.getCreatedAt())
                .build();
    }
}
