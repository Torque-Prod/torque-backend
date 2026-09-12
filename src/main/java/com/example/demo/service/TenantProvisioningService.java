package com.example.demo.service;

import com.example.demo.config.tenant.Tenant;
import com.example.demo.config.tenant.TenantDataSourceConfig;
import com.example.demo.config.tenant.TenantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@RequiredArgsConstructor
@Slf4j
public class TenantProvisioningService {

    private final TenantRepository tenantRepository;
    private final TenantDataSourceConfig tenantDataSourceConfig;
    private final DataSource masterDataSource; // Used to create new DBs

    public Tenant provisionTenant(String tenantId, String shopName, String plan) {
        // 1. Check if exists
        if (tenantRepository.findByTenantId(tenantId).isPresent()) {
            throw new RuntimeException("Tenant already exists");
        }

        String dbName = "torque_shop_" + tenantId.replace("-", "_");

        // 2. Create database
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(masterDataSource);
            jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS " + dbName);
            log.info("Created database {} for tenant {}", dbName, tenantId);
        } catch (Exception e) {
            log.error("Failed to create database for tenant {}", tenantId, e);
            throw new RuntimeException("Failed to provision tenant database");
        }

        // 3. Save to registry
        Tenant tenant = Tenant.builder()
                .tenantId(tenantId)
                .dbName(dbName)
                .dbHost("localhost")
                .dbPort("3307")
                .dbUser("root")
                .dbPassword("123")
                .shopName(shopName)
                .plan(plan)
                .active(true)
                .build();
        
        tenant = tenantRepository.save(tenant);

        // 4. Hot reload the data source map
        tenantDataSourceConfig.addTenant(tenant);

        log.info("Successfully provisioned tenant {}", tenantId);
        return tenant;
    }
}
