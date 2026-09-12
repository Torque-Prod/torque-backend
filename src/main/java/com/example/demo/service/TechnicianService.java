package com.example.demo.service;

import com.example.demo.dto.TechnicianDto;
import com.example.demo.entity.RepairJob;
import com.example.demo.entity.Technician;
import com.example.demo.repository.RepairJobRepository;
import com.example.demo.repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final RepairJobRepository repairJobRepository;

    public TechnicianDto createTechnician(TechnicianDto request) {
        Technician technician = new Technician();
        technician.setName(request.getName());
        technician.setPhone(request.getPhone());
        return toDto(technicianRepository.save(technician));
    }

    public List<TechnicianDto> getAllTechnicians() {
        return technicianRepository.findAll()
                .stream()
                .map(this::toDtoWithStatus)
                .collect(Collectors.toList());
    }

    public TechnicianDto updateTechnician(Long id, TechnicianDto request) {
        Technician technician = technicianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technician not found with id: " + id));
        technician.setName(request.getName());
        technician.setPhone(request.getPhone());
        return toDtoWithStatus(technicianRepository.save(technician));
    }

    public void deleteTechnician(Long id) {
        technicianRepository.deleteById(id);
    }

    /** Basic DTO without job status (used internally) */
    private TechnicianDto toDto(Technician tech) {
        return TechnicianDto.builder()
                .id(tech.getId())
                .name(tech.getName())
                .phone(tech.getPhone())
                .status("Available")
                .jobNumber(null)
                .build();
    }

    /** DTO enriched with live job assignment status */
    private TechnicianDto toDtoWithStatus(Technician tech) {
        List<RepairJob> activeJobs = repairJobRepository.findActiveJobsByTechnicianId(tech.getId());

        boolean hasActiveJob = !activeJobs.isEmpty();
        String status    = hasActiveJob ? "In Job"    : "Available";
        String jobNumber = hasActiveJob ? activeJobs.get(0).getJobNumber() : null;

        return TechnicianDto.builder()
                .id(tech.getId())
                .name(tech.getName())
                .phone(tech.getPhone())
                .status(status)
                .jobNumber(jobNumber)
                .build();
    }

    public List<Technician> getTechniciansDetails() {
        return technicianRepository.findAllTechniciansWithJobs();
    }
}
