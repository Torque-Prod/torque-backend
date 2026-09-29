package com.example.demo.dto;

import com.example.demo.entity.enums.TableStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTableDto {
    private Long id;
    private String tableNumber;
    private Integer capacity;
    private String section;
    private String qrToken;
    private String qrImageBase64;  // PNG image as base64 data URI for frontend display
    private TableStatus status;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
