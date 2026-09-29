package com.example.demo.controller;

import com.example.demo.dto.CreateTableRequest;
import com.example.demo.dto.RestaurantTableDto;
import com.example.demo.entity.enums.TableStatus;
import com.example.demo.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurant/tables")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final RestaurantTableService tableService;

    // ─── Staff endpoints (JWT required) ────────────────────────────────────────

    @GetMapping
    public ResponseEntity<List<RestaurantTableDto>> getAll() {
        return ResponseEntity.ok(tableService.getAllActiveTables());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTableDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.getTableById(id));
    }

    @PostMapping
    public ResponseEntity<RestaurantTableDto> create(@RequestBody CreateTableRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tableService.createTable(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTableDto> update(
            @PathVariable Long id,
            @RequestBody CreateTableRequest request) {
        return ResponseEntity.ok(tableService.updateTable(id, request));
    }

    @PostMapping("/{id}/regenerate-qr")
    public ResponseEntity<RestaurantTableDto> regenerateQr(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.regenerateQr(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RestaurantTableDto> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        TableStatus status = TableStatus.valueOf(body.get("status").toUpperCase());
        return ResponseEntity.ok(tableService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        tableService.deactivateTable(id);
        return ResponseEntity.noContent().build();
    }

    // ─── Public endpoint (no JWT — used by customer QR scan) ───────────────────

    @GetMapping("/public/by-token/{qrToken}")
    public ResponseEntity<RestaurantTableDto> getByToken(@PathVariable String qrToken) {
        return ResponseEntity.ok(tableService.getByQrToken(qrToken));
    }
}
