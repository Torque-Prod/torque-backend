package com.example.demo.controller;

import com.example.demo.dto.RestaurantCategoryDto;
import com.example.demo.service.RestaurantCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant/categories")
@RequiredArgsConstructor
public class RestaurantCategoryController {

    private final RestaurantCategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<RestaurantCategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping
    public ResponseEntity<RestaurantCategoryDto> create(@RequestBody RestaurantCategoryDto dto) {
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantCategoryDto> update(@PathVariable Long id, @RequestBody RestaurantCategoryDto dto) {
        return ResponseEntity.ok(categoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
