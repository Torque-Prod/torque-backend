package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequest {

    // Customer info — entered by cashier at the checkout dialog
    private String customerName;    // required
    private String customerPhone;   // optional
    private Long   customerId;      // optional — link to existing Customer record

    // Payment
    private String paymentMethod;   // CASH / CARD / TRANSFER / CREDIT
    private Double paidAmount;      // amount handed over (cash) or charged (card)
    private Double discount;        // optional, default 0
    private Double tax;             // optional, default 0

    private String notes;           // optional receipt note
}
