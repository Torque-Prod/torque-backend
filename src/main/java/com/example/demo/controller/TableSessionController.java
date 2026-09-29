package com.example.demo.controller;

import com.example.demo.dto.TableSessionDto;
import com.example.demo.service.TableSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/restaurant/table-sessions")
@RequiredArgsConstructor
public class TableSessionController {

    private final TableSessionService tableSessionService;

    @GetMapping("/active/table/{tableId}")
    public ResponseEntity<TableSessionDto> getActiveSession(@PathVariable Long tableId) {
        Optional<TableSessionDto> session = tableSessionService.findActiveSession(tableId);
        return session.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping("/open/{tableId}")
    public ResponseEntity<TableSessionDto> openSession(@PathVariable Long tableId) {
        TableSessionDto session = tableSessionService.openSession(tableId);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }

    @PostMapping("/{sessionId}/close")
    public ResponseEntity<TableSessionDto> closeSession(@PathVariable Long sessionId) {
        TableSessionDto session = tableSessionService.closeSession(sessionId);
        return ResponseEntity.ok(session);
    }
}
