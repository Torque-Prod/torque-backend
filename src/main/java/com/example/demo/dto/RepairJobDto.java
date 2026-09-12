package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairJobDto {
    private Long id;
    private String jobNumber;

    // Customer summary (no full object — avoid circular refs)
    private Long customerId;
    private String customerName;
    private String customerPhone;

    private Long technicianId;
    private String technicianName;

    private String itemName;
    private String modelNumber;
    private String serialNumber;
    private String problemDescription;
    private LocalDate receivedDate;
    private LocalDate expectedDate;
    private String status;
    private LocalDateTime createdAt;

    // Billing
    private Double serviceCharge;
    private Double partsTotal;
    private Double netTotal;
    private boolean paid;
    private List<UsedPartDto> usedParts;

    public RepairJobDto(Long id, String jobNumber, Long customerId, String customerName, String customerPhone, Long technicianId, String technicianName) {
        this.id = id;
        this.jobNumber = jobNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.technicianId = technicianId;
        this.technicianName = technicianName;
    }
}
