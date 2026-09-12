package com.example.demo.service;

import com.example.demo.entity.JobStatusConfig;
import com.example.demo.repository.JobStatusConfigRepository;
import com.example.demo.repository.RepairJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobStatusConfigService {

    private final JobStatusConfigRepository repository;
    private final RepairJobRepository repairJobRepository;

    @PostConstruct
    public void seedDefaults() {
        if (repository.count() == 0) {
            repository.save(JobStatusConfig.builder()
                    .statusKey("PENDING").displayName("Pending").icon("pi pi-clock")
                    .color("#f59e0b").bg("#fffbeb").border("#fde68a")
                    .isDefault(true).sortOrder(1).build());
            
            repository.save(JobStatusConfig.builder()
                    .statusKey("IN_PROGRESS").displayName("In Progress").icon("pi pi-spin pi-spinner")
                    .color("#8b5cf6").bg("#f5f3ff").border("#ddd6fe")
                    .sortOrder(2).build());

            repository.save(JobStatusConfig.builder()
                    .statusKey("WAITING_FOR_PARTS").displayName("Waiting for Parts").icon("pi pi-exclamation-circle")
                    .color("#f97316").bg("#fff7ed").border("#fed7aa")
                    .sortOrder(3).build());

            repository.save(JobStatusConfig.builder()
                    .statusKey("COMPLETED").displayName("Completed").icon("pi pi-check-circle")
                    .color("#22c55e").bg("#f0fdf4").border("#bbf7d0")
                    .sortOrder(4).build());

            repository.save(JobStatusConfig.builder()
                    .statusKey("DELIVERED").displayName("Delivered").icon("pi pi-send")
                    .color("#6b7280").bg("#f9fafb").border("#e5e7eb")
                    .isTerminal(true).sortOrder(5).build());
        }
    }

    public List<JobStatusConfig> getAll() {
        return repository.findAll();
    }

    @Transactional
    public JobStatusConfig create(JobStatusConfig config) {
        if (config.isDefault()) {
            clearOtherDefaults();
        }
        return repository.save(config);
    }

    @Transactional
    public JobStatusConfig update(Long id, JobStatusConfig updated) {
        JobStatusConfig existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status config not found"));
        
        existing.setDisplayName(updated.getDisplayName());
        existing.setColor(updated.getColor());
        existing.setBg(updated.getBg());
        existing.setBorder(updated.getBorder());
        existing.setIcon(updated.getIcon());
        existing.setSortOrder(updated.getSortOrder());
        existing.setTerminal(updated.isTerminal());
        existing.setSendSms(updated.isSendSms());

        if (updated.isDefault() && !existing.isDefault()) {
            clearOtherDefaults();
            existing.setDefault(true);
        }

        return repository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        JobStatusConfig config = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status config not found"));
        
        // Safety check: Is this status in use?
        if (repairJobRepository.existsByStatus(config.getStatusKey())) {
            throw new RuntimeException("Cannot delete status '" + config.getDisplayName() + "' because it is currently assigned to repair jobs.");
        }

        if (config.isDefault()) {
            throw new RuntimeException("Cannot delete the default status. Set another status as default first.");
        }

        repository.delete(config);
    }

    private void clearOtherDefaults() {
        repository.findByIsDefaultTrue().ifPresent(c -> {
            c.setDefault(false);
            repository.save(c);
        });
    }

    public JobStatusConfig getDefaultStatus() {
        return repository.findByIsDefaultTrue()
                .orElseThrow(() -> new RuntimeException("No default status configured"));
    }
}
