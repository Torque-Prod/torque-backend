package com.example.demo.repository;

import com.example.demo.entity.RestaurantTable;
import com.example.demo.entity.enums.TableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

    List<RestaurantTable> findAllByIsActiveTrueOrderByTableNumberAsc();

    Optional<RestaurantTable> findByQrToken(String qrToken);

    boolean existsByTableNumber(String tableNumber);

    List<RestaurantTable> findAllByIsActiveTrueAndStatusOrderByTableNumberAsc(TableStatus status);

    List<RestaurantTable> findAllByIsActiveTrueAndSectionOrderByTableNumberAsc(String section);
}
