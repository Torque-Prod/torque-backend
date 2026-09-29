package com.example.demo.entity;

import com.example.demo.entity.enums.TableSessionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "table_sessions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The physical table this session belongs to
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_table_id", nullable = false)
    private RestaurantTable restaurantTable;

    // Snapshot — kept so even if table is renamed, history stays readable
    @Column(name = "table_number", nullable = false)
    private String tableNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TableSessionStatus status = TableSessionStatus.OPEN;

    @CreationTimestamp
    @Column(name = "opened_at", nullable = false, updatable = false)
    private LocalDateTime openedAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    // Staff username who processed checkout
    @Column(name = "closed_by")
    private String closedBy;

    // Cascade: fetch all orders in this session
    @OneToMany(mappedBy = "tableSession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<RestaurantOrder> orders = new ArrayList<>();
}
