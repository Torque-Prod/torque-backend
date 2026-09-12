package com.example.demo.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "repair_job_parts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepairJobPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "repair_job_id", nullable = false)
    private Long repairJobId;

    @Column(name = "part_id", nullable = false)
    private Long partId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double unitPrice; // Price at the time of repair

    public Double getTotalPrice() {
        return quantity * unitPrice;
    }
}
