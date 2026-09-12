package com.example.demo.repository;

import com.example.demo.entity.RestaurantMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantMenuItemRepository extends JpaRepository<RestaurantMenuItem, Long> {
    List<RestaurantMenuItem> findAllByAvailableTrueOrderByCategoryAscNameAsc();
    List<RestaurantMenuItem> findAllByOrderByCategoryAscNameAsc();
}
