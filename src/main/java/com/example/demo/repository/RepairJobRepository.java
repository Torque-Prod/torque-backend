package com.example.demo.repository;

import com.example.demo.entity.RepairJob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepairJobRepository extends JpaRepository<RepairJob, Long>, RepairJobRepositoryCustom {

    // Check if any jobs exist for a customer (used before deleting a customer)
    boolean existsByCustomerId(Long customerId);

    // Filter by status
    List<RepairJob> findByStatus(String status);

    // Check if status is used
    boolean existsByStatus(String status);

    // Find the first active (non-terminal) job for a technician
    @Query("SELECT r FROM RepairJob r WHERE r.technicianId = :technicianId " +
           "AND r.technicianId IS NOT NULL " +
           "AND r.status NOT IN (SELECT sc.statusKey FROM JobStatusConfig sc WHERE sc.isTerminal = true) " +
           "ORDER BY r.createdAt DESC")
    List<RepairJob> findActiveJobsByTechnicianId(@Param("technicianId") Long technicianId);

    long count();
}
