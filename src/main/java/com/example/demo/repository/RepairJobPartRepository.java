package com.example.demo.repository;

import com.example.demo.entity.RepairJobPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairJobPartRepository extends JpaRepository<RepairJobPart, Long> {
    List<RepairJobPart> findByRepairJobId(Long repairJobId);
}
