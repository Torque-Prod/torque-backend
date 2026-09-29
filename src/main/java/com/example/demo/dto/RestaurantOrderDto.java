package com.example.demo.dto;

import com.example.demo.entity.enums.OrderItemStatus;
import com.example.demo.entity.enums.OrderSource;
import com.example.demo.entity.enums.OrderStatus;
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
public class RestaurantOrderDto {

    private Long id;
    private Long sessionId;
    private String orderNumber;
    private OrderSource source;
    private OrderStatus status;
    private String notes;
    private List<ItemDto> items;
    private double itemTotal;       // sum of all item line totals in this order
    private LocalDateTime createdAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemDto {
        private Long id;
        private Long menuItemId;
        private String itemName;
        private double unitPrice;
        private int quantity;
        private String notes;
        private OrderItemStatus status;
        private double lineTotal;
    }
}
