package com.example.demo.service;

import com.example.demo.dto.MenuItemDto;
import com.example.demo.entity.MenuItem;
import com.example.demo.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuItemRepository menuItemRepository;

    public List<MenuItemDto> getAllMenus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // If no user is authenticated, return empty or safe menus
        if (authentication == null || !authentication.isAuthenticated()) {
            return menuItemRepository.findAllByOrderBySectionAscOrderIndexAsc()
                    .stream()
                    .filter(m -> m.getRequiredPrivilege() == null || m.getRequiredPrivilege().isEmpty())
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        return menuItemRepository.findAllByOrderBySectionAscOrderIndexAsc()
                .stream()
                .filter(m -> hasAccess(m, isAdmin, authorities))
                .map(this::mapToDto)
                .collect(Collectors.toList());
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
