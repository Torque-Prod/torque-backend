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
public class CreateOrderRequest {

    private Long tableId;           // which table to open/find session for
    private List<OrderItemRequest> items;
    private String notes;           // optional order-level note

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemRequest {
        private Long menuItemId;    // must exist and be available
        private Integer quantity;   // minimum 1
        private String notes;       // e.g. "no onions"
    }
}
