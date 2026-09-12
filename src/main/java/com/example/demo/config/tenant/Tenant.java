package com.example.demo.config.tenant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tenants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id", unique = true, nullable = false)
    private String tenantId;

    @Column(name = "db_name", nullable = false)
    private String dbName;

    @Column(name = "db_host")
    private String dbHost;

    @Column(name = "db_port")
    private String dbPort;

    @Column(name = "db_user")
    private String dbUser;

    @Column(name = "db_password")
    private String dbPassword;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_address")
    private String shopAddress;

    @Column(name = "shop_phone")
    private String shopPhone;

    private String plan;

    private boolean active;

    @Column(name = "business_type", nullable = false)
    @Builder.Default
    private String businessType = "REPAIR_CENTER"; // REPAIR_CENTER | RESTAURANT

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
