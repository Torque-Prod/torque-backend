package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTableRequest {
    private String tableNumber;  // e.g. "T-01"
    private Integer capacity;
    private String section;      // optional
}
