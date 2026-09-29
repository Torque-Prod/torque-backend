package com.example.demo.dto;

import com.example.demo.entity.enums.TableSessionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableSessionDto {
    private Long id;
    private Long tableId;
    private String tableNumber;
    private TableSessionStatus status;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private String closedBy;
    private int orderCount;        // number of non-cancelled orders
    private double runningTotal;   // sum of all non-cancelled items
}
