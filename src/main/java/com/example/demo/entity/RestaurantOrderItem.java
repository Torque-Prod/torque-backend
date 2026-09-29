package com.example.demo.entity;

import com.example.demo.entity.enums.OrderItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_order_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private RestaurantOrder order;

    // Nullable: menu item may be deleted after ordering — snapshot keeps history intact
    @Column(name = "menu_item_id")
    private Long menuItemId;

    // SNAPSHOT of name at order time — never join back to RestaurantMenuItem for this
    @Column(name = "item_name", nullable = false)
    private String itemName;

    // SNAPSHOT of price at order time — menu prices change, this must not change
    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    @Builder.Default
    private Integer quantity = 1;

    // Per-item special instructions, e.g. "no onions", "extra spicy"
    @Column(columnDefinition = "TEXT")
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private OrderItemStatus status = OrderItemStatus.PENDING;

    // Computed helper — not persisted
    @Transient
    public double getLineTotal() {
        return (unitPrice != null ? unitPrice : 0.0) * (quantity != null ? quantity : 0);
    }
}
