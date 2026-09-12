package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartDto {
    private Long id;
    private String name;
    private Double price;
    private Integer stockQuantity;
    private String category;
    private String containerLocation;
    private boolean hasImage;
}
