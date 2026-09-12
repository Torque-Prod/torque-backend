package com.example.demo.controller;

import com.example.demo.config.tenant.Tenant;
import com.example.demo.config.tenant.TenantRepository;
import com.example.demo.service.TenantProvisioningService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/tenants")
@RequiredArgsConstructor
public class TenantAdminController {

    private final TenantProvisioningService provisioningService;
    private final TenantRepository tenantRepository;

    @PostMapping
    public ResponseEntity<Tenant> provisionTenant(@RequestBody ProvisionTenantRequest request) {
        return ResponseEntity.ok(provisioningService.provisionTenant(
                request.getTenantId(),
                request.getShopName(),
                request.getPlan()
        ));
    }

    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants() {
        return ResponseEntity.ok(tenantRepository.findAll());
    }

    @Data
    public static class ProvisionTenantRequest {
        private String tenantId;
        private String shopName;
        private String plan = "pro";
    }
}
