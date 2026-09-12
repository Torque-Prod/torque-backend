package com.example.demo.controller;

import com.example.demo.dto.RestaurantMenuItemDto;
import com.example.demo.service.RestaurantMenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant/menu-items")
@RequiredArgsConstructor
public class RestaurantMenuItemController {

    private final RestaurantMenuItemService menuItemService;

    // Used by management page — returns all items including unavailable
    @GetMapping
    public ResponseEntity<List<RestaurantMenuItemDto>> getAll() {
        return ResponseEntity.ok(menuItemService.getAll());
    }

    // Used by POS — returns only available items
    @GetMapping("/available")
    public ResponseEntity<List<RestaurantMenuItemDto>> getAvailable() {
        return ResponseEntity.ok(menuItemService.getAvailable());
    }

    @PostMapping
    public ResponseEntity<RestaurantMenuItemDto> create(
            @RequestPart("item") RestaurantMenuItemDto dto,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.ok(menuItemService.create(dto, image));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantMenuItemDto> update(
            @PathVariable Long id,
            @RequestPart("item") RestaurantMenuItemDto dto,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.ok(menuItemService.update(id, dto, image));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        menuItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
