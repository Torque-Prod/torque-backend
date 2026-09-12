package com.example.demo.controller;

import com.example.demo.dto.CreateSaleRequest;
import com.example.demo.dto.GridSearchDto;
import com.example.demo.dto.PagedSaleResponse;
import com.example.demo.dto.SaleDto;
import com.example.demo.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('MANAGE_INVENTORY', 'VIEW_INVENTORY') or hasRole('ADMIN')")
    public ResponseEntity<SaleDto> checkout(@RequestBody CreateSaleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.checkout(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('VIEW_INVENTORY', 'MANAGE_INVENTORY', 'VIEW_DASHBOARD') or hasRole('ADMIN')")
    public ResponseEntity<List<SaleDto>> getAll() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('VIEW_INVENTORY', 'MANAGE_INVENTORY', 'VIEW_DASHBOARD') or hasRole('ADMIN')")
    public ResponseEntity<PagedSaleResponse> search(@RequestBody GridSearchDto dto) {
        return ResponseEntity.ok(saleService.searchSales(dto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('VIEW_INVENTORY', 'MANAGE_INVENTORY') or hasRole('ADMIN')")
    public ResponseEntity<SaleDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @GetMapping("/invoice/{invoiceNumber}")
    @PreAuthorize("hasAnyAuthority('VIEW_INVENTORY', 'MANAGE_INVENTORY') or hasRole('ADMIN')")
    public ResponseEntity<SaleDto> getByInvoiceNumber(@PathVariable String invoiceNumber) {
        return ResponseEntity.ok(saleService.getSaleByInvoiceNumber(invoiceNumber));
    }
}
