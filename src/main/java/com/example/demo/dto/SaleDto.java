package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {
    private Long id;
    private String invoiceNumber;
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private String paymentMethod;
    private Double subtotal;
    private Double discount;
    private Double tax;
    private Double netTotal;
    private Double paidAmount;
    private Double changeAmount;
    private String soldBy;
    private String notes;
    private LocalDateTime createdAt;
    private List<SaleItemDto> items;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaleItemDto {
        private Long id;
        private Long partId;
        private String partName;
        private Double unitPrice;
        private Integer quantity;
        private Double totalPrice;
    }
}
