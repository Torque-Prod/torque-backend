package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsedPartDto {
    private Long id;
    private Long partId;
    private String partName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
}
