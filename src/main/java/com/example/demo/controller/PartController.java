package com.example.demo.controller;

import com.example.demo.dto.CsvImportResultDto;
import com.example.demo.dto.GridSearchDto;
import com.example.demo.dto.PagedPartResponse;
import com.example.demo.dto.PartDto;
import com.example.demo.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parts")
@RequiredArgsConstructor
public class PartController {

    private final PartService partService;

    @PostMapping
    public ResponseEntity<PartDto> create(@RequestBody PartDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partService.createPart(request));
    }

    @GetMapping
    public ResponseEntity<List<PartDto>> getAll() {
        return ResponseEntity.ok(partService.getAllParts());
    }

    @PostMapping("/search")
    public ResponseEntity<PagedPartResponse> search(@RequestBody GridSearchDto dto) {
        return ResponseEntity.ok(partService.searchParts(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(partService.getPartById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartDto> update(@PathVariable Long id, @RequestBody PartDto request) {
        return ResponseEntity.ok(partService.updatePart(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        partService.deletePart(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Void> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            partService.uploadImage(id, file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        com.example.demo.entity.Part part = partService.getImage(id);
        if (part.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.CONTENT_TYPE, part.getImageType())
                .body(part.getImageData());
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        partService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    // POST /api/v1/parts/import-csv — Bulk import parts from a CSV file
    @PostMapping("/import-csv")
    public ResponseEntity<CsvImportResultDto> importCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String filename = file.getOriginalFilename();
        if (filename == null || !filename.toLowerCase().endsWith(".csv")) {
            return ResponseEntity.badRequest().build();
        }
        try {
            CsvImportResultDto result = partService.importPartsFromCsv(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
