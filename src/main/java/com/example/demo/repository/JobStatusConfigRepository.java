package com.example.demo.repository;

import com.example.demo.entity.JobStatusConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobStatusConfigRepository extends JpaRepository<JobStatusConfig, Long> {
    Optional<JobStatusConfig> findByStatusKey(String statusKey);
    Optional<JobStatusConfig> findByIsDefaultTrue();
}
