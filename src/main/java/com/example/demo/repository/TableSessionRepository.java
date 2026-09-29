package com.example.demo.repository;

import com.example.demo.entity.TableSession;
import com.example.demo.entity.enums.TableSessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableSessionRepository extends JpaRepository<TableSession, Long> {

    Optional<TableSession> findByRestaurantTableIdAndStatus(Long tableId, TableSessionStatus status);

    boolean existsByRestaurantTableIdAndStatus(Long tableId, TableSessionStatus status);
}
