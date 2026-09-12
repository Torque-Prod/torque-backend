package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.GridSearchDto;
import com.example.demo.dto.PagedCustomerResponse;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RepairJobRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RepairJobRepository repairJobRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // ─── CREATE ────────────────────────────────────────────
    public CustomerDto createCustomer(CustomerRequest request) {
        if (customerRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("A customer with phone number '" + request.getPhone() + "' already exists.");
        }

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());

        return toDto(customerRepository.save(customer));
    }

    // ─── READ ALL ──────────────────────────────────────────
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ─── SEARCH with filters + pagination ──────────────────
    public PagedCustomerResponse searchCustomers(GridSearchDto dto) {

        String baseFrom = """
                FROM customers c
                WHERE 1=1
                """;

        StringBuilder where = new StringBuilder();
        List<Object> params = new ArrayList<>();

        // Global filter
        if (dto.getGlobalFilter() != null && !dto.getGlobalFilter().isBlank()) {
            where.append(" AND (c.name LIKE ? OR c.phone LIKE ? OR c.address LIKE ?)");
            String p = "%" + dto.getGlobalFilter().trim() + "%";
            params.add(p); params.add(p); params.add(p);
        }

        // Column filters
        if (dto.getFilters() != null) {
            var f = dto.getFilters();
            if (hasValue(f.get("name")))    { where.append(" AND c.name LIKE ?");    params.add("%" + f.get("name").getValue()    + "%"); }
            if (hasValue(f.get("phone")))   { where.append(" AND c.phone LIKE ?");   params.add("%" + f.get("phone").getValue()   + "%"); }
            if (hasValue(f.get("address"))) { where.append(" AND c.address LIKE ?"); params.add("%" + f.get("address").getValue() + "%"); }
        }

        // Count
        String countSql = "SELECT COUNT(*) " + baseFrom + where;
        Query countQuery = entityManager.createNativeQuery(countSql);
        setParams(countQuery, params);
        long totalRecords = ((Number) countQuery.getSingleResult()).longValue();

        // Sort
        String sortClause = " ORDER BY c.created_at DESC";
        if (dto.getSortField() != null && !dto.getSortField().isBlank()) {
            String col = switch (dto.getSortField()) {
                case "name"      -> "c.name";
                case "phone"     -> "c.phone";
                case "address"   -> "c.address";
                case "createdAt" -> "c.created_at";
                default          -> "c.created_at";
            };
            String dir = (dto.getSortOrder() != null && dto.getSortOrder() == -1) ? "DESC" : "ASC";
            sortClause = " ORDER BY " + col + " " + dir;
        }

        // Data
        String dataSql = "SELECT c.id, c.name, c.phone, c.address, c.created_at " + baseFrom + where + sortClause;
        Query dataQuery = entityManager.createNativeQuery(dataSql);
        setParams(dataQuery, params);

        int first = (dto.getFirst() != null && dto.getFirst() >= 0) ? dto.getFirst() : 0;
        int rows  = (dto.getRows()  != null && dto.getRows()  > 0)  ? dto.getRows()  : 25;
        dataQuery.setFirstResult(first);
        dataQuery.setMaxResults(rows);

        List<Object[]> results = dataQuery.getResultList();
        List<CustomerDto> data = results.stream().map(row -> CustomerDto.builder()
                .id(((Number) row[0]).longValue())
                .name((String) row[1])
                .phone((String) row[2])
                .address((String) row[3])
                .createdAt(toLocalDateTime(row[4]))
                .build()
        ).collect(Collectors.toList());

        return PagedCustomerResponse.builder()
                .data(data)
                .totalRecords(totalRecords)
                .build();
    }

    // ─── READ ONE ──────────────────────────────────────────
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return toDto(customer);
    }

    // ─── SEARCH ────────────────────────────────────────────
    public List<CustomerDto> searchCustomers(String keyword) {
        List<Customer> byName  = customerRepository.findByNameContainingIgnoreCase(keyword);
        List<Customer> byPhone = customerRepository.findByPhoneContaining(keyword);

        // Merge both lists, removing duplicates by ID
        return Stream.concat(byName.stream(), byPhone.stream())
                .distinct()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ─── UPDATE ────────────────────────────────────────────
    public CustomerDto updateCustomer(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        // Check phone is not taken by another customer
        if (customerRepository.existsByPhoneAndIdNot(request.getPhone(), id)) {
            throw new RuntimeException("Phone number '" + request.getPhone() + "' is already used by another customer.");
        }

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());

        return toDto(customerRepository.save(customer));
    }

    // ─── DELETE ────────────────────────────────────────────
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        // Guard: block delete if customer has repair jobs
        if (repairJobRepository.existsByCustomerId(id)) {
            throw new RuntimeException("Cannot delete customer because they have existing repair jobs.");
        }

        customerRepository.delete(customer);
    }

    // ─── Mapper ────────────────────────────────────────────
    private CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .createdAt(customer.getCreatedAt())
                .build();
    }

    // ─── Helpers ───────────────────────────────────────────
    private java.time.LocalDateTime toLocalDateTime(Object value) {
        if (value == null)                           return null;
        if (value instanceof java.time.LocalDateTime ldt) return ldt;
        if (value instanceof java.sql.Timestamp ts)  return ts.toLocalDateTime();
        return null;
    }

    private boolean hasValue(com.example.demo.dto.Filter f) {
        return f != null && f.getValue() != null && !f.getValue().toString().isBlank();
    }

    private void setParams(Query query, List<Object> params) {
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i));
        }
    }
}
