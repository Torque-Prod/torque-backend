package com.example.demo.controller;

import com.example.demo.dto.CreateRepairJobRequest;
import com.example.demo.dto.GridSearchDto;
import com.example.demo.dto.PagedRepairJobResponse;
import com.example.demo.dto.RepairJobDto;
import com.example.demo.dto.UpdateStatusRequest;
import com.example.demo.service.ExcelExportService;
import com.example.demo.service.RepairJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/repair-jobs")
@RequiredArgsConstructor
public class RepairJobController {

    private final RepairJobService repairJobService;
    private final ExcelExportService excelExportService;

    // GET /api/v1/repair-jobs/export — Export all jobs matching current filters to Excel
    @PostMapping("/export")
    public ResponseEntity<byte[]> exportJobs(@RequestBody GridSearchDto gridSearchDto) throws IOException, ParseException {
        // Run search with unlimited rows to get all filtered data
        gridSearchDto.setFirst(0);
        gridSearchDto.setRows(Integer.MAX_VALUE);
        List<RepairJobDto> jobs = repairJobService.searchJobs(gridSearchDto).getData();

        byte[] excelBytes = excelExportService.exportRepairJobs(jobs);

        String filename = "repair-jobs-" + java.time.LocalDate.now() + ".xlsx";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", filename);
        headers.setContentLength(excelBytes.length);

        return ResponseEntity.ok().headers(headers).body(excelBytes);
    }

    // POST /api/v1/repair-jobs — Create job
    @PostMapping
    public ResponseEntity<RepairJobDto> createJob(@RequestBody CreateRepairJobRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repairJobService.createRepairJob(request));
    }

    // GET /api/v1/repair-jobs — Get all jobs
    @GetMapping
    public ResponseEntity<List<RepairJobDto>> getAllJobs() {
        return ResponseEntity.ok(repairJobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairJobDto> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(repairJobService.getJobById(id));
    }

    @PostMapping("/search")
    public ResponseEntity<PagedRepairJobResponse> searchJobs(@RequestBody GridSearchDto gridSearchDto) throws ParseException {
        return ResponseEntity.ok(repairJobService.searchJobs(gridSearchDto));
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<RepairJobDto>> getByStatus(@RequestParam String status) {
        return ResponseEntity.ok(repairJobService.getJobsByStatus(status));
    }

    // PATCH /api/v1/repair-jobs/{id}/status — Update status only
    @PatchMapping("/{id}/status")
    public ResponseEntity<RepairJobDto> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusRequest request) {
        return ResponseEntity.ok(repairJobService.updateStatus(id, request));
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<RepairJobDto> assignTechnician(
            @PathVariable Long id,
            @RequestParam Long technicianId) {
        return ResponseEntity.ok(repairJobService.assignTechnician(id, technicianId));
    }

    // ─── BILLING & PARTS ────────────────────────────────────
    @PostMapping("/{id}/parts")
    public ResponseEntity<RepairJobDto> addPart(
            @PathVariable Long id,
            @RequestParam Long partId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(repairJobService.addPartToJob(id, partId, quantity));
    }

    @DeleteMapping("/{id}/parts/{jobPartId}")
    public ResponseEntity<RepairJobDto> removePart(
            @PathVariable Long id,
            @PathVariable Long jobPartId) {
        return ResponseEntity.ok(repairJobService.removePartFromJob(id, jobPartId));
    }

    @PatchMapping("/{id}/service-charge")
    public ResponseEntity<RepairJobDto> updateServiceCharge(
            @PathVariable Long id,
            @RequestParam Double amount) {
        return ResponseEntity.ok(repairJobService.updateServiceCharge(id, amount));
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<RepairJobDto> markAsPaid(@PathVariable Long id) {
        return ResponseEntity.ok(repairJobService.markAsPaid(id));
    }
}
