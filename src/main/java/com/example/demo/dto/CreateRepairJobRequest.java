package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRepairJobRequest {
    private Long customerId;
    private String itemName;
    private String modelNumber;
    private String serialNumber;
    private String problemDescription;
    private LocalDate receivedDate;
    private LocalDate expectedDate;
}
