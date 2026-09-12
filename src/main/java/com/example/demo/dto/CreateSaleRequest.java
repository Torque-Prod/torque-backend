package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSaleRequest {
    private Long customerId;
    private String customerName;
    private String customerPhone;
    private String paymentMethod; // CASH, CARD, TRANSFER, CREDIT
    private Double discount;
    private Double tax;
    private Double paidAmount;
    private String notes;
    private List<SaleItemRequest> items;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaleItemRequest {
        private Long partId;          // used when itemType = PART
        private String itemType;      // "PART" or "MENU_ITEM"
        private String itemName;      // used when itemType = MENU_ITEM (name snapshot)
        private Integer quantity;
        private Double unitPrice;
    }
}
