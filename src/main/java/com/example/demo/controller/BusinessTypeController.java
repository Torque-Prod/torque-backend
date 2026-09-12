package com.example.demo.controller;

import com.example.demo.config.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/business-type")
public class BusinessTypeController {

    private final JdbcTemplate masterJdbc;

    // Inject the master datasource bean directly — bypasses the tenant routing datasource
    public BusinessTypeController(@Qualifier("masterDataSource") DataSource masterDataSource) {
        this.masterJdbc = new JdbcTemplate(masterDataSource);
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getBusinessType() {
        String tenantId = TenantContext.get();
        Map<String, String> response = new HashMap<>();

        try {
            Map<String, Object> tenantData = masterJdbc.queryForMap(
                "SELECT business_type, shop_name, shop_address, shop_phone FROM tenants WHERE tenant_id = ?",
                tenantId
            );
            
            String businessType = (String) tenantData.get("business_type");
            response.put("type", businessType != null ? businessType : "REPAIR_CENTER");
            response.put("shopName", (String) tenantData.get("shop_name"));
            response.put("shopAddress", (String) tenantData.get("shop_address"));
            response.put("shopPhone", (String) tenantData.get("shop_phone"));
            
        } catch (Exception e) {
            // Tenant not found or columns don't exist yet — safe defaults
            response.put("type", "REPAIR_CENTER");
            response.put("shopName", "Torque Auto Care");
            response.put("shopAddress", "");
            response.put("shopPhone", "");
        }

        return ResponseEntity.ok(response);
    }
}
