package com.example.demo.repository;

import com.example.demo.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    
    List<Part> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Part p WHERE p.stockQuantity <= 5")
    List<Part> findLowStockParts();
}
