package com.example.demo.controller;

import com.example.demo.dto.CheckoutRequest;
import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.dto.RestaurantOrderDto;
import com.example.demo.dto.SaleDto;
import com.example.demo.entity.enums.OrderItemStatus;
import com.example.demo.entity.enums.OrderSource;
import com.example.demo.entity.enums.OrderStatus;
import com.example.demo.service.RestaurantOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant/orders")
@RequiredArgsConstructor
public class RestaurantOrderController {

    private final RestaurantOrderService orderService;

    @PostMapping
    public ResponseEntity<RestaurantOrderDto> createOrder(
            @RequestBody CreateOrderRequest request,
            @RequestParam(defaultValue = "WAITER") OrderSource source) {
        RestaurantOrderDto order = orderService.createOrder(request, source);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<RestaurantOrderDto>> getOrdersBySession(@PathVariable Long sessionId) {
        return ResponseEntity.ok(orderService.getOrdersBySession(sessionId));
    }

    @PutMapping("/items/{itemId}/status")
    public ResponseEntity<RestaurantOrderDto> updateItemStatus(
            @PathVariable Long itemId,
            @RequestParam OrderItemStatus status) {
        return ResponseEntity.ok(orderService.updateItemStatus(itemId, status));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<RestaurantOrderDto> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }

    @PostMapping("/session/{sessionId}/checkout")
    public ResponseEntity<SaleDto> checkoutSession(
            @PathVariable Long sessionId,
            @RequestBody(required = false) CheckoutRequest checkoutReq) {
        SaleDto sale = orderService.checkoutSession(sessionId, checkoutReq);
        return ResponseEntity.ok(sale);
    }
}
