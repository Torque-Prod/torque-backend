package com.example.demo.config;

import com.example.demo.config.tenant.TenantContext;
import com.example.demo.entity.MenuItem;
import com.example.demo.entity.Privilege;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.MenuItemRepository;
import com.example.demo.repository.PrivilegeRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;
    private final MenuItemRepository menuItemRepository;
    // Use master JdbcTemplate directly to avoid routing to tenant DB
    private final JdbcTemplate masterJdbc;

    public DataInitializer(
            RoleRepository roleRepository,
            PrivilegeRepository privilegeRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            org.springframework.jdbc.core.JdbcTemplate jdbcTemplate,
            MenuItemRepository menuItemRepository,
            @Qualifier("masterDataSource") DataSource masterDataSource) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
        this.menuItemRepository = menuItemRepository;
        this.masterJdbc = new JdbcTemplate(masterDataSource);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Data initialization has been moved to SQL scripts.
        // See rbac_migration.sql

        if (menuItemRepository.count() == 0) {
            // Read business_type directly from master DB to avoid tenant routing issue
            String tenantId = TenantContext.get();
            String businessType = "REPAIR_CENTER"; // safe default
            if (tenantId != null) {
                try {
                    String result = masterJdbc.queryForObject(
                        "SELECT business_type FROM tenants WHERE tenant_id = ?",
                        String.class, tenantId
                    );
                    if (result != null) businessType = result;
                } catch (Exception e) {
                    // Column may not exist yet — stay with default
                }
            }

            if ("RESTAURANT".equals(businessType)) {
                menuItemRepository.saveAll(Arrays.asList(
                    MenuItem.builder().label("Dashboard").icon("pi pi-home").routerLink("/web/dashboard").requiredPrivilege("").section("MAIN").orderIndex(1).build(),
                    MenuItem.builder().label("POS & Orders").icon("pi pi-shopping-cart").routerLink("/web/sales").requiredPrivilege("VIEW_INVENTORY").section("MAIN").orderIndex(2).build(),
                    MenuItem.builder().label("Menu Management").icon("pi pi-list").routerLink("/web/restaurant-menu").requiredPrivilege("MANAGE_INVENTORY").section("MAIN").orderIndex(3).build(),
                    MenuItem.builder().label("Staff Access").icon("pi pi-id-card").routerLink("/web/staff").requiredPrivilege("MANAGE_ROLES").section("SYSTEM").orderIndex(1).build(),
                    MenuItem.builder().label("Role Management").icon("pi pi-users").routerLink("/web/roles").requiredPrivilege("MANAGE_ROLES").section("SYSTEM").orderIndex(2).build()
                ));
            } else {
                // REPAIR_CENTER (default)
                menuItemRepository.saveAll(Arrays.asList(
                    MenuItem.builder().label("Dashboard").icon("pi pi-home").routerLink("/web/dashboard").requiredPrivilege("").section("MAIN").orderIndex(1).build(),
                    MenuItem.builder().label("Jobs").icon("pi pi-wrench").routerLink("/web/repair-jobs").requiredPrivilege("VIEW_REPAIR_JOBS").section("MAIN").badgeType("activeJobs").orderIndex(2).build(),
                    MenuItem.builder().label("Customers").icon("pi pi-users").routerLink("/web/customers").requiredPrivilege("VIEW_CUSTOMERS").section("MAIN").orderIndex(3).build(),
                    MenuItem.builder().label("Inventory").icon("pi pi-box").routerLink("/web/inventory").requiredPrivilege("VIEW_INVENTORY").section("MAIN").badgeType("lowStock").orderIndex(4).build(),
                    MenuItem.builder().label("POS & Sales").icon("pi pi-shopping-cart").routerLink("/web/sales").requiredPrivilege("VIEW_INVENTORY").section("MAIN").orderIndex(5).build(),
                    MenuItem.builder().label("Statuses").icon("pi pi-tag").routerLink("/web/settings?tab=statuses").requiredPrivilege("MANAGE_ROLES").section("SYSTEM").orderIndex(1).build(),
                    MenuItem.builder().label("Staff Access").icon("pi pi-id-card").routerLink("/web/staff").requiredPrivilege("MANAGE_ROLES").section("SYSTEM").orderIndex(2).build(),
                    MenuItem.builder().label("Role Management").icon("pi pi-users").routerLink("/web/roles").requiredPrivilege("MANAGE_ROLES").section("SYSTEM").orderIndex(3).build()
                ));
            }
        }
    }
}
