package com.example.demo.service;

import com.example.demo.dto.RestaurantCategoryDto;
import com.example.demo.entity.RestaurantCategory;
import com.example.demo.repository.RestaurantCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantCategoryService {

    private final RestaurantCategoryRepository categoryRepository;

    public List<RestaurantCategoryDto> getAll() {
        return categoryRepository.findAllByOrderByDisplayOrderAsc()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    public RestaurantCategoryDto create(RestaurantCategoryDto dto) {
        RestaurantCategory entity = RestaurantCategory.builder()
                .name(dto.getName())
                .displayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0)
                .build();
        return toDto(categoryRepository.save(entity));
    }

    public RestaurantCategoryDto update(Long id, RestaurantCategoryDto dto) {
        RestaurantCategory entity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found: " + id));
        entity.setName(dto.getName());
        if (dto.getDisplayOrder() != null) entity.setDisplayOrder(dto.getDisplayOrder());
        return toDto(categoryRepository.save(entity));
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    private RestaurantCategoryDto toDto(RestaurantCategory c) {
        return RestaurantCategoryDto.builder()
                .id(c.getId())
                .name(c.getName())
                .displayOrder(c.getDisplayOrder())
                .build();
    }
}
