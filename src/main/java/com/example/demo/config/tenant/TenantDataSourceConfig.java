package com.example.demo.config.tenant;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@Slf4j
public class TenantDataSourceConfig {

    @Value("${spring.datasource.master.url}")
    private String masterUrl;

    @Value("${spring.datasource.master.username}")
    private String masterUsername;

    @Value("${spring.datasource.master.password}")
    private String masterPassword;

    @Value("${spring.datasource.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String driverClassName;

    private final Map<Object, Object> resolvedDataSources = new ConcurrentHashMap<>();
    private TenantRoutingDataSource routingDataSource;

    @Bean
    public DataSource masterDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(masterUrl);
        dataSource.setUsername(masterUsername);
        dataSource.setPassword(masterPassword);
        return dataSource;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        DataSource masterDb = masterDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(masterDb);

        // Load all active tenants
        List<Map<String, Object>> tenants;
        try {
            tenants = jdbcTemplate.queryForList("SELECT * FROM tenants WHERE active = true");
        } catch (Exception e) {
            log.warn("Could not load tenants from master database. Table might not exist yet. Error: {}", e.getMessage());
            tenants = List.of();
        }

        for (Map<String, Object> tenant : tenants) {
            String tenantId = (String) tenant.get("tenant_id");
            DataSource tenantDataSource = createTenantDataSource(tenant);
            resolvedDataSources.put(tenantId, tenantDataSource);
            log.info("Loaded data source for tenant: {}", tenantId);
        }

        // Add a default fallback data source (can point to master or a dummy db)
        resolvedDataSources.put("default", masterDb); 

        routingDataSource = new TenantRoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(masterDb);
        routingDataSource.setTargetDataSources(resolvedDataSources);
        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }

    private DataSource createTenantDataSource(Map<String, Object> tenant) {
        String dbName = (String) tenant.get("db_name");
        String dbHost = tenant.get("db_host") != null ? (String) tenant.get("db_host") : "localhost";
        String dbPort = tenant.get("db_port") != null ? (String) tenant.get("db_port") : "3307";
        String dbUser = tenant.get("db_user") != null ? (String) tenant.get("db_user") : masterUsername;
        String dbPass = tenant.get("db_password") != null ? (String) tenant.get("db_password") : masterPassword;

        String url = String.format("jdbc:mysql://%s:%s/%s?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true", dbHost, dbPort, dbName);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPass);
        
        // Good defaults for multi-tenant so we don't exhaust connections
        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(2);

        return dataSource;
    }

    /**
     * Call this method to dynamically add a new tenant at runtime without restarting.
     */
    public void addTenant(Tenant tenant) {
        Map<String, Object> tenantMap = new HashMap<>();
        tenantMap.put("tenant_id", tenant.getTenantId());
        tenantMap.put("db_name", tenant.getDbName());
        tenantMap.put("db_host", tenant.getDbHost());
        tenantMap.put("db_port", tenant.getDbPort());
        tenantMap.put("db_user", tenant.getDbUser());
        tenantMap.put("db_password", tenant.getDbPassword());

        DataSource tenantDataSource = createTenantDataSource(tenantMap);
        resolvedDataSources.put(tenant.getTenantId(), tenantDataSource);
        
        routingDataSource.setTargetDataSources(resolvedDataSources);
        routingDataSource.afterPropertiesSet();
        
        log.info("Dynamically added data source for new tenant: {}", tenant.getTenantId());
    }
}
