package com.example.demo.service;

import com.example.demo.dto.TableSessionDto;
import com.example.demo.entity.RestaurantTable;
import com.example.demo.entity.TableSession;
import com.example.demo.entity.enums.OrderItemStatus;
import com.example.demo.entity.enums.OrderStatus;
import com.example.demo.entity.enums.TableSessionStatus;
import com.example.demo.entity.enums.TableStatus;
import com.example.demo.repository.RestaurantTableRepository;
import com.example.demo.repository.TableSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableSessionService {

    private final TableSessionRepository sessionRepository;
    private final RestaurantTableRepository tableRepository;

    @Transactional(readOnly = true)
    public Optional<TableSessionDto> findActiveSession(Long tableId) {
        return sessionRepository.findByRestaurantTableIdAndStatus(tableId, TableSessionStatus.OPEN)
                .map(this::toDto);
    }

    @Transactional(readOnly = true)
    public TableSession getSessionEntity(Long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session not found: ID " + sessionId));
    }

    @Transactional(readOnly = true)
    public Optional<TableSession> getActiveSessionEntity(Long tableId) {
        return sessionRepository.findByRestaurantTableIdAndStatus(tableId, TableSessionStatus.OPEN);
    }

    @Transactional
    public TableSession getOrOpenActiveSessionEntity(Long tableId) {
        return sessionRepository.findByRestaurantTableIdAndStatus(tableId, TableSessionStatus.OPEN)
                .orElseGet(() -> openSessionEntity(tableId));
    }

    @Transactional
    public TableSession openSessionEntity(Long tableId) {
        Optional<TableSession> existing = sessionRepository.findByRestaurantTableIdAndStatus(tableId, TableSessionStatus.OPEN);
        if (existing.isPresent()) {
            return existing.get();
        }

        RestaurantTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Table not found: ID " + tableId));

        if (!table.getIsActive()) {
            throw new IllegalStateException("Table " + table.getTableNumber() + " is deactivated.");
        }

        TableSession session = TableSession.builder()
                .restaurantTable(table)
                .tableNumber(table.getTableNumber())
                .status(TableSessionStatus.OPEN)
                .build();

        TableSession saved = sessionRepository.save(session);

        // Flip table status to OCCUPIED
        table.setStatus(TableStatus.OCCUPIED);
        tableRepository.save(table);

        return saved;
    }

    @Transactional
    public TableSessionDto openSession(Long tableId) {
        TableSession session = openSessionEntity(tableId);
        return toDto(session);
    }

    @Transactional
    public TableSessionDto closeSession(Long sessionId) {
        String closedBy = "Cashier";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getName() != null) {
            closedBy = auth.getName();
        }
        closeSession(sessionId, closedBy);
        TableSession closedSession = getSessionEntity(sessionId);
        return toDto(closedSession);
    }

    @Transactional
    public void closeSession(Long sessionId, String closedBy) {
        TableSession session = getSessionEntity(sessionId);

        if (session.getStatus() != TableSessionStatus.OPEN) {
            return; // already closed
        }

        session.setStatus(TableSessionStatus.CLOSED);
        session.setClosedAt(LocalDateTime.now());
        session.setClosedBy(closedBy);
        sessionRepository.save(session);

        // Flip table to NEEDS_CLEANING
        RestaurantTable table = session.getRestaurantTable();
        table.setStatus(TableStatus.NEEDS_CLEANING);
        tableRepository.save(table);
    }

    public TableSessionDto toDto(TableSession session) {
        long orderCount = session.getOrders() == null ? 0 :
                session.getOrders().stream()
                        .filter(o -> o.getStatus() != OrderStatus.CANCELLED)
                        .count();

        double total = session.getOrders() == null ? 0.0 :
                session.getOrders().stream()
                        .filter(o -> o.getStatus() != OrderStatus.CANCELLED)
                        .flatMap(o -> o.getItems() == null ? java.util.stream.Stream.empty() : o.getItems().stream())
                        .filter(item -> item.getStatus() != OrderItemStatus.CANCELLED)
                        .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                        .sum();

        return TableSessionDto.builder()
                .id(session.getId())
                .tableId(session.getRestaurantTable().getId())
                .tableNumber(session.getTableNumber())
                .status(session.getStatus())
                .openedAt(session.getOpenedAt())
                .closedAt(session.getClosedAt())
                .closedBy(session.getClosedBy())
                .orderCount((int) orderCount)
                .runningTotal(total)
                .build();
    }
}
