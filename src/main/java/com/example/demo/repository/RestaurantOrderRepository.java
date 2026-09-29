package com.example.demo.repository;

import com.example.demo.entity.RestaurantOrder;
import com.example.demo.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrder, Long> {

    List<RestaurantOrder> findAllByTableSessionIdOrderByCreatedAtAsc(Long sessionId);

    // Count orders created on or after a given timestamp — used for order number generation
    @Query("SELECT COUNT(o) FROM RestaurantOrder o WHERE o.createdAt >= :startOfDay")
    long countOrdersSince(@Param("startOfDay") LocalDateTime startOfDay);

    List<RestaurantOrder> findAllByTableSessionIdAndStatusNot(Long sessionId, OrderStatus status);
}
