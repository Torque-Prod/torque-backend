package com.example.demo.controller;

import com.example.demo.entity.JobStatusConfig;
import com.example.demo.service.JobStatusConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings/statuses")
@RequiredArgsConstructor
public class JobStatusConfigController {

    private final JobStatusConfigService service;

    @GetMapping
    public ResponseEntity<List<JobStatusConfig>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<JobStatusConfig> create(@RequestBody JobStatusConfig config) {
        return ResponseEntity.ok(service.create(config));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobStatusConfig> update(@PathVariable Long id, @RequestBody JobStatusConfig config) {
        return ResponseEntity.ok(service.update(id, config));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
