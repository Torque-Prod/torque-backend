package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianDto {
    private Long id;
    private String name;
    private String phone;

    /** "Available" or "In Job" */
    private String status;

    /** Job number of the active job, null if Available */
    private String jobNumber;
}
