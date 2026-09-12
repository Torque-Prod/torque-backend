package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepairJobService {

    private final RepairJobRepository repairJobRepository;
    private final CustomerRepository customerRepository;
    private final TechnicianRepository technicianRepository;
    private final PartRepository partRepository;
    private final RepairJobPartRepository repairJobPartRepository;
    private final PartService partService;
    private final JobStatusConfigService statusConfigService;
    private final JobStatusConfigRepository statusConfigRepository;
    private final SmsNotificationService smsNotificationService;

    // ─── CREATE ────────────────────────────────────────────
    public RepairJobDto createRepairJob(CreateRepairJobRequest request) {
        // Validate customer exists
        customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + request.getCustomerId()));

        long count = repairJobRepository.count() + 1;
        String jobNumber = String.format("JOB-%04d", count);

        // Fetch the default status
        JobStatusConfig defaultStatus = statusConfigService.getDefaultStatus();

        RepairJob job = RepairJob.builder()
                .jobNumber(jobNumber)
                .customerId(request.getCustomerId())
                .itemName(request.getItemName())
                .modelNumber(request.getModelNumber())
                .serialNumber(request.getSerialNumber())
                .problemDescription(request.getProblemDescription())
                .receivedDate(request.getReceivedDate() != null ? request.getReceivedDate() : LocalDate.now())
                .expectedDate(request.getExpectedDate())
                .status(defaultStatus.getStatusKey())
                .build();

        return toDto(repairJobRepository.save(job));
    }

    public List<RepairJobDto> getAllJobs() {
        return repairJobRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public RepairJobDto getJobById(Long id) {
        RepairJob job = repairJobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair job not found with id: " + id));
        return toDto(job);
    }

    public PagedRepairJobResponse searchJobs(GridSearchDto gridSearchDto) throws ParseException {
        return repairJobRepository.searchJobs(gridSearchDto);
    }

    public List<RepairJobDto> getJobsByStatus(String status) {
        return repairJobRepository.findByStatus(status)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ─── ASSIGN TECHNICIAN ──────────────────────────────────
    @Transactional
    public RepairJobDto assignTechnician(Long id, Long technicianId) {
        RepairJob job = repairJobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair job not found with id: " + id));

        JobStatusConfig currentStatus = statusConfigRepository.findByStatusKey(job.getStatus())
                .orElseThrow(() -> new RuntimeException("Current status configuration not found"));

        if (currentStatus.isTerminal()) {
            throw new RuntimeException("Cannot assign technician to a completed/delivered job.");
        }

        // Validate technician exists
        technicianRepository.findById(technicianId)
                .orElseThrow(() -> new RuntimeException("Technician not found with id: " + technicianId));

        job.setTechnicianId(technicianId);
        return toDto(repairJobRepository.save(job));
    }

    // ─── PARTS & BILLING ────────────────────────────────────
    @Transactional
    public RepairJobDto addPartToJob(Long id, Long partId, Integer quantity) {
        RepairJob job = repairJobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair job not found"));

        JobStatusConfig currentStatus = statusConfigRepository.findByStatusKey(job.getStatus())
                .orElseThrow(() -> new RuntimeException("Status config not found"));

        if (currentStatus.isTerminal()) {
            throw new RuntimeException("Cannot add parts to a terminal state job.");
        }

        Part part = partRepository.findById(partId)
                .orElseThrow(() -> new RuntimeException("Part not found"));

        // Use PartService to handle stock deduction and validation
        partService.reduceStock(partId, quantity);

        RepairJobPart jobPart = RepairJobPart.builder()
                .repairJobId(job.getId())
                .partId(partId)
                .quantity(quantity)
                .unitPrice(part.getPrice())
                .build();

        repairJobPartRepository.save(jobPart);
        return toDto(job);
    }

    @Transactional
    public RepairJobDto removePartFromJob(Long jobId, Long jobPartId) {
        RepairJobPart jobPart = repairJobPartRepository.findById(jobPartId)
                .orElseThrow(() -> new RuntimeException("Part record not found"));

        RepairJob job = repairJobRepository.findById(jobPart.getRepairJobId())
                .orElseThrow(() -> new RuntimeException("Repair job not found"));

        JobStatusConfig currentStatus = statusConfigRepository.findByStatusKey(job.getStatus())
                .orElseThrow(() -> new RuntimeException("Status config not found"));

        if (currentStatus.isTerminal()) {
            throw new RuntimeException("Cannot modify parts of a terminal state job.");
        }

        // Restore stock
        partService.restoreStock(jobPart.getPartId(), jobPart.getQuantity());

        repairJobPartRepository.delete(jobPart);
        return toDto(repairJobRepository.findById(jobId).get());
    }

    @Transactional
    public RepairJobDto updateServiceCharge(Long id, Double amount) {
        RepairJob job = repairJobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair job not found"));

        JobStatusConfig currentStatus = statusConfigRepository.findByStatusKey(job.getStatus())
                .orElseThrow(() -> new RuntimeException("Status config not found"));

        if (currentStatus.isTerminal()) {
            throw new RuntimeException("Cannot update bill of a terminal state job.");
        }

        job.setServiceCharge(amount);
        return toDto(repairJobRepository.save(job));
    }

    @Transactional
    public RepairJobDto markAsPaid(Long id) {
        RepairJob job = repairJobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair job not found"));
        job.setPaid(true);
        return toDto(repairJobRepository.save(job));
    }

    // ─── UPDATE STATUS WITH BUSINESS RULES ──────────────────
    @Transactional
    public RepairJobDto updateStatus(Long id, UpdateStatusRequest request) {
        RepairJob job = repairJobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair job not found with id: " + id));

        JobStatusConfig currentStatusConfig = statusConfigRepository.findByStatusKey(job.getStatus())
                .orElseThrow(() -> new RuntimeException("Current status config not found"));

        if (currentStatusConfig.isTerminal()) {
            throw new RuntimeException("Cannot change status of a terminal state job.");
        }

        // Verify the next status exists
        JobStatusConfig nextStatusConfig = statusConfigRepository.findByStatusKey(request.getStatus())
                .orElseThrow(() -> new RuntimeException("Target status '" + request.getStatus() + "' does not exist."));

        // Logic Rule: Must be PAID before terminal status (like DELIVERED)
        if (nextStatusConfig.isTerminal()) {
            if (job.getPaid() == null || !job.getPaid()) {
                throw new RuntimeException("Job must be PAID before it can be moved to a terminal status.");
            }
        }

        if (request.getTechnicianId() != null) {
            technicianRepository.findById(request.getTechnicianId())
                    .orElseThrow(() -> new RuntimeException("Technician not found"));
            job.setTechnicianId(request.getTechnicianId());
        }

        job.setStatus(request.getStatus());
        RepairJob saved = repairJobRepository.save(job);

        // ─── Send SMS only if this status has sendSms=true ────
        try {
            if (nextStatusConfig.isSendSms()) {
                Customer customer = customerRepository.findById(saved.getCustomerId()).orElse(null);
                if (customer != null) {
                    smsNotificationService.sendStatusUpdate(
                            customer.getPhone(),
                            customer.getName(),
                            saved.getJobNumber(),
                            nextStatusConfig.getDisplayName()
                    );
                }
            }
        } catch (Exception e) {
            // Never let SMS failure break the job update
        }

        return toDto(saved);
    }

    private RepairJobDto toDto(RepairJob job) {
        // Fetch related names via repositories
        Customer customer = customerRepository.findById(job.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + job.getCustomerId()));

        String technicianName = "Unassigned";
        if (job.getTechnicianId() != null) {
            technicianName = technicianRepository.findById(job.getTechnicianId())
                    .map(Technician::getName)
                    .orElse("Unassigned");
        }

        // Fetch used parts by repairJobId
        List<RepairJobPart> rawParts = repairJobPartRepository.findByRepairJobId(job.getId());

        List<UsedPartDto> usedParts = rawParts.stream()
                .map(up -> {
                    Part part = partRepository.findById(up.getPartId())
                            .orElseThrow(() -> new RuntimeException("Part not found with id: " + up.getPartId()));
                    return UsedPartDto.builder()
                            .id(up.getId())
                            .partId(up.getPartId())
                            .partName(part.getName())
                            .quantity(up.getQuantity())
                            .unitPrice(up.getUnitPrice())
                            .totalPrice(up.getTotalPrice())
                            .build();
                })
                .collect(Collectors.toList());

        Double partsTotal = usedParts.stream().mapToDouble(UsedPartDto::getTotalPrice).sum();
        Double serviceCharge = job.getServiceCharge() != null ? job.getServiceCharge() : 0.0;

        return RepairJobDto.builder()
                .id(job.getId())
                .jobNumber(job.getJobNumber())
                .customerId(job.getCustomerId())
                .customerName(customer.getName())
                .customerPhone(customer.getPhone())
                .technicianId(job.getTechnicianId())
                .technicianName(technicianName)
                .itemName(job.getItemName())
                .modelNumber(job.getModelNumber())
                .serialNumber(job.getSerialNumber())
                .problemDescription(job.getProblemDescription())
                .receivedDate(job.getReceivedDate())
                .expectedDate(job.getExpectedDate())
                .status(job.getStatus())
                .createdAt(job.getCreatedAt())
                .serviceCharge(serviceCharge)
                .partsTotal(partsTotal)
                .netTotal(serviceCharge + partsTotal)
                .paid(job.getPaid() != null && job.getPaid())
                .usedParts(usedParts)
                .build();
    }
}
