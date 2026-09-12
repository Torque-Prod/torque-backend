package com.example.demo.repository;

import com.example.demo.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    List<Technician> findByNameContainingIgnoreCase(String name);


    @Query("SELECT t FROM Technician t")
    List<Technician> findAllTechniciansWithJobs();

}
