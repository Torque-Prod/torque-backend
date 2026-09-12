package com.example.demo.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_status_configs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobStatusConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String statusKey; // Internal ID like 'PENDING'

    @Column(nullable = false)
    private String displayName; // User visible name

    private String color;  // Text color
    private String bg;     // Badge background
    private String border; // Badge border
    private String icon;   // PrimeNG icon class

    private boolean isDefault; // Used for new jobs
    private boolean isTerminal; // Blocks edits (like Delivered)
    private boolean sendSms;   // Send SMS to customer when job enters this status

    private int sortOrder;
}
