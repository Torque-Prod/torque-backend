package com.example.demo.controller;

import com.example.demo.dto.TechnicianDto;
import com.example.demo.entity.Technician;
import com.example.demo.service.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/technicians")
@RequiredArgsConstructor
public class TechnicianController {

    private final TechnicianService technicianService;

    @PostMapping
    public ResponseEntity<TechnicianDto> create(@RequestBody TechnicianDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(technicianService.createTechnician(request));
    }

    @GetMapping
    public ResponseEntity<List<TechnicianDto>> getAll() {
        return ResponseEntity.ok(technicianService.getAllTechnicians());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicianDto> update(@PathVariable Long id, @RequestBody TechnicianDto request) {
        return ResponseEntity.ok(technicianService.updateTechnician(id, request));
    }

    @GetMapping("/get_technicians_details")
    public ResponseEntity<List<Technician>> getTechniciansDetails() {
        return ResponseEntity.ok(technicianService.getTechniciansDetails());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        technicianService.deleteTechnician(id);
        return ResponseEntity.noContent().build();
    }
}
