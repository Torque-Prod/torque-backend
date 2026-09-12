package com.example.demo.service;

import com.example.demo.dto.RestaurantMenuItemDto;
import com.example.demo.entity.RestaurantCategory;
import com.example.demo.entity.RestaurantMenuItem;
import com.example.demo.repository.RestaurantCategoryRepository;
import com.example.demo.repository.RestaurantMenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantMenuItemService {

    private final RestaurantMenuItemRepository menuItemRepository;
    private final RestaurantCategoryRepository categoryRepository;

    // Returns all items (for POS — only available ones)
    public List<RestaurantMenuItemDto> getAvailable() {
        return menuItemRepository.findAllByAvailableTrueOrderByCategoryAscNameAsc()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    // Returns all items including unavailable (for management page)
    public List<RestaurantMenuItemDto> getAll() {
        return menuItemRepository.findAllByOrderByCategoryAscNameAsc()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    public RestaurantMenuItemDto create(RestaurantMenuItemDto dto, MultipartFile image) throws IOException {
        RestaurantMenuItem entity = buildEntity(new RestaurantMenuItem(), dto, image);
        return toDto(menuItemRepository.save(entity));
    }

    public RestaurantMenuItemDto update(Long id, RestaurantMenuItemDto dto, MultipartFile image) throws IOException {
        RestaurantMenuItem entity = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found: " + id));
        buildEntity(entity, dto, image);
        return toDto(menuItemRepository.save(entity));
    }

    public void delete(Long id) {
        menuItemRepository.deleteById(id);
    }

    private RestaurantMenuItem buildEntity(RestaurantMenuItem entity, RestaurantMenuItemDto dto, MultipartFile image) throws IOException {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setAvailable(dto.getAvailable() != null ? dto.getAvailable() : true);

        if (dto.getCategoryId() != null) {
            RestaurantCategory category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategoryId()));
            entity.setCategory(category);
        }

        if (image != null && !image.isEmpty()) {
            entity.setImageData(image.getBytes());
            entity.setImageType(image.getContentType());
        }
        return entity;
    }

    private RestaurantMenuItemDto toDto(RestaurantMenuItem m) {
        String base64 = null;
        if (m.getImageData() != null) {
            base64 = "data:" + m.getImageType() + ";base64," + Base64.getEncoder().encodeToString(m.getImageData());
        }
        return RestaurantMenuItemDto.builder()
                .id(m.getId())
                .name(m.getName())
                .description(m.getDescription())
                .price(m.getPrice())
                .categoryId(m.getCategory() != null ? m.getCategory().getId() : null)
                .categoryName(m.getCategory() != null ? m.getCategory().getName() : null)
                .available(m.getAvailable())
                .imageType(m.getImageType())
                .imageBase64(base64)
                .build();
    }
}
