package com.example.demo.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "repair_jobs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_number", unique = true, nullable = false)
    private String jobNumber;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "technician_id")
    private Long technicianId;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "model_number")
    private String modelNumber;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "problem_description", columnDefinition = "TEXT")
    private String problemDescription;

    @Column(name = "received_date", nullable = false)
    private LocalDate receivedDate;

    @Column(name = "expected_date")
    private LocalDate expectedDate;

    @Column(name = "service_charge")
    private Double serviceCharge = 0.0;

    @Column(name = "is_paid")
    private Boolean paid = false;

    @Column(nullable = false)
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
