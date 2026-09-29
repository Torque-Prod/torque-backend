package com.example.demo.service;

import com.example.demo.config.tenant.TenantContext;
import com.example.demo.dto.MenuItemDto;
import com.example.demo.entity.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuItemRepository menuItemRepository;
    private final JdbcTemplate masterJdbc;

    public MenuService(
            MenuItemRepository menuItemRepository,
            @Qualifier("masterDataSource") DataSource masterDataSource) {
        this.menuItemRepository = menuItemRepository;
        this.masterJdbc = new JdbcTemplate(masterDataSource);
    }

    public List<MenuItemDto> getAllMenus() {
        String businessType = getTenantBusinessType();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<MenuItem> allMenus = menuItemRepository.findAllByOrderBySectionAscOrderIndexAsc();

        // If no user is authenticated, return empty or safe menus
        if (authentication == null || !authentication.isAuthenticated()) {
            return allMenus.stream()
                    .filter(m -> isAllowedForBusinessType(m, businessType))
                    .filter(m -> m.getRequiredPrivilege() == null || m.getRequiredPrivilege().isEmpty())
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        return allMenus.stream()
                .filter(m -> isAllowedForBusinessType(m, businessType))
                .filter(m -> hasAccess(m, isAdmin, authorities))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private String getTenantBusinessType() {
        String tenantId = TenantContext.get();
        if (tenantId == null) return "REPAIR_CENTER";
        try {
            String type = masterJdbc.queryForObject(
                "SELECT business_type FROM tenants WHERE tenant_id = ?",
                String.class, tenantId
            );
            return type != null ? type : "REPAIR_CENTER";
        } catch (Exception e) {
            return "REPAIR_CENTER";
        }
    }

    private boolean isAllowedForBusinessType(MenuItem menuItem, String businessType) {
        String link = menuItem.getRouterLink();
        if (link == null) return true;

        if ("RESTAURANT".equals(businessType)) {
            // Exclude Repair Center specific items
            if (link.contains("repair-jobs") || link.contains("inventory") || link.contains("customers") || link.contains("statuses")) {
                return false;
            }
        } else {
            // Exclude Restaurant specific items
            if (link.contains("restaurant-tables") || link.contains("restaurant-menu")) {
                return false;
            }
        }
        return true;
    }

    private boolean hasAccess(MenuItem menuItem, boolean isAdmin, Collection<? extends GrantedAuthority> authorities) {
        if (menuItem.getRequiredPrivilege() == null || menuItem.getRequiredPrivilege().isEmpty()) {
            return true;
        }
        if (isAdmin) {
            return true;
        }
        return authorities.stream().anyMatch(a -> a.getAuthority().equals(menuItem.getRequiredPrivilege()));
    }

    private MenuItemDto mapToDto(MenuItem menuItem) {
        return MenuItemDto.builder()
                .id(menuItem.getId())
                .label(menuItem.getLabel())
                .icon(menuItem.getIcon())
                .routerLink(menuItem.getRouterLink())
                .requiredPrivilege(menuItem.getRequiredPrivilege())
                .section(menuItem.getSection())
                .badgeType(menuItem.getBadgeType())
                .orderIndex(menuItem.getOrderIndex())
                .build();
    }
}
