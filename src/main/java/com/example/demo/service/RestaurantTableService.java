package com.example.demo.service;

import com.example.demo.dto.CreateTableRequest;
import com.example.demo.dto.RestaurantTableDto;
import com.example.demo.entity.RestaurantTable;
import com.example.demo.entity.enums.TableStatus;
import com.example.demo.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final RestaurantTableRepository tableRepository;
    private final QrCodeService qrCodeService;

    public List<RestaurantTableDto> getAllActiveTables() {
        return tableRepository.findAllByIsActiveTrueOrderByTableNumberAsc()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public RestaurantTableDto getTableById(Long id) {
        RestaurantTable table = findActiveById(id);
        return toDto(table);
    }

    /**
     * Resolves a qrToken to its table DTO.
     * Used by the public customer-facing endpoint when scanning QR.
     */
    public RestaurantTableDto getByQrToken(String qrToken) {
        RestaurantTable table = tableRepository.findByQrToken(qrToken)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or expired QR code."));
        if (!table.getIsActive()) {
            throw new IllegalArgumentException("This table is no longer active. Please ask staff for assistance.");
        }
        return toDto(table);
    }

    @Transactional
    public RestaurantTableDto createTable(CreateTableRequest request) {
        validateRequest(request);

        if (tableRepository.existsByTableNumber(request.getTableNumber().trim().toUpperCase())) {
            throw new IllegalArgumentException("Table number '" + request.getTableNumber() + "' already exists.");
        }

        RestaurantTable table = RestaurantTable.builder()
                .tableNumber(request.getTableNumber().trim().toUpperCase())
                .capacity(request.getCapacity())
                .section(request.getSection() != null ? request.getSection().trim() : null)
                .qrToken(UUID.randomUUID().toString())
                .status(TableStatus.AVAILABLE)
                .isActive(true)
                .build();

        return toDto(tableRepository.save(table));
    }

    @Transactional
    public RestaurantTableDto updateTable(Long id, CreateTableRequest request) {
        validateRequest(request);
        RestaurantTable table = findActiveById(id);

        // Allow same table number on update (same record), reject if it belongs to a different table
        String newNumber = request.getTableNumber().trim().toUpperCase();
        if (!table.getTableNumber().equals(newNumber) && tableRepository.existsByTableNumber(newNumber)) {
            throw new IllegalArgumentException("Table number '" + newNumber + "' already exists.");
        }

        table.setTableNumber(newNumber);
        table.setCapacity(request.getCapacity());
        table.setSection(request.getSection() != null ? request.getSection().trim() : null);

        return toDto(tableRepository.save(table));
    }

    /**
     * Rotates the qrToken — invalidates the old QR code.
     * Old QR scans will get a "not found" response, showing the friendly message.
     */
    @Transactional
    public RestaurantTableDto regenerateQr(Long id) {
        RestaurantTable table = findActiveById(id);
        table.setQrToken(UUID.randomUUID().toString());
        return toDto(tableRepository.save(table));
    }

    /**
     * Updates table status (e.g. cashier marks NEEDS_CLEANING → AVAILABLE after cleanup).
     */
    @Transactional
    public RestaurantTableDto updateStatus(Long id, TableStatus status) {
        RestaurantTable table = findActiveById(id);
        table.setStatus(status);
        return toDto(tableRepository.save(table));
    }

    /**
     * Soft-delete — sets isActive = false.
     * Does not delete the DB record so historical session data stays intact.
     */
    @Transactional
    public void deactivateTable(Long id) {
        RestaurantTable table = findActiveById(id);
        table.setIsActive(false);
        tableRepository.save(table);
    }

    // ─── Helpers ───────────────────────────────────────────────────────────────

    private RestaurantTable findActiveById(Long id) {
        RestaurantTable table = tableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Table not found: ID " + id));
        if (!table.getIsActive()) {
            throw new IllegalArgumentException("Table ID " + id + " is deactivated.");
        }
        return table;
    }

    private void validateRequest(CreateTableRequest request) {
        if (request.getTableNumber() == null || request.getTableNumber().isBlank()) {
            throw new IllegalArgumentException("Table number is required.");
        }
        if (request.getCapacity() == null || request.getCapacity() < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }
    }

    private RestaurantTableDto toDto(RestaurantTable table) {
        return RestaurantTableDto.builder()
                .id(table.getId())
                .tableNumber(table.getTableNumber())
                .capacity(table.getCapacity())
                .section(table.getSection())
                .qrToken(table.getQrToken())
                .qrImageBase64(qrCodeService.generateTableQrBase64(table.getQrToken()))
                .status(table.getStatus())
                .isActive(table.getIsActive())
                .createdAt(table.getCreatedAt())
                .updatedAt(table.getUpdatedAt())
                .build();
    }
}
