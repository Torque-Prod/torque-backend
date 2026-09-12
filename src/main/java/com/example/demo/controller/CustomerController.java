package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerRequest;
import com.example.demo.dto.GridSearchDto;
import com.example.demo.dto.PagedCustomerResponse;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // POST /api/v1/customers — Create customer
    @PostMapping
    @PreAuthorize("hasAnyAuthority('MANAGE_CUSTOMERS', 'CREATE_CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.createCustomer(request));
    }

    // GET /api/v1/customers — Get all customers
    @GetMapping
    @PreAuthorize("hasAnyAuthority('VIEW_CUSTOMERS', 'MANAGE_CUSTOMERS', 'READ_CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    // GET /api/v1/customers/{id} — Get single customer
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('VIEW_CUSTOMERS', 'MANAGE_CUSTOMERS', 'READ_CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    // GET /api/v1/customers/search?keyword=john — Search by name or phone (legacy)
    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('VIEW_CUSTOMERS', 'MANAGE_CUSTOMERS', 'READ_CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<List<CustomerDto>> searchCustomers(@RequestParam String keyword) {
        return ResponseEntity.ok(customerService.searchCustomers(keyword));
    }

    // POST /api/v1/customers/search — Paged search with filters
    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('VIEW_CUSTOMERS', 'MANAGE_CUSTOMERS', 'READ_CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<PagedCustomerResponse> searchCustomersPaged(@RequestBody GridSearchDto gridSearchDto) {
        return ResponseEntity.ok(customerService.searchCustomers(gridSearchDto));
    }

    // PUT /api/v1/customers/{id} — Update customer
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGE_CUSTOMERS', 'UPDATE_CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }

    // DELETE /api/v1/customers/{id} — Delete customer
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGE_CUSTOMERS', 'DELETE_CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}

