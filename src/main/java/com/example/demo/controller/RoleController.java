package com.example.demo.controller;

import com.example.demo.entity.Privilege;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/privileges")
    @PreAuthorize("hasAuthority('MANAGE_ROLES') or hasRole('ADMIN')")
    public ResponseEntity<List<Privilege>> getAllPrivileges() {
        return ResponseEntity.ok(roleService.getAllPrivileges());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGE_ROLES') or hasRole('ADMIN')")
    public ResponseEntity<Role> createRole(@RequestBody RoleRequest request) {
        return ResponseEntity.ok(roleService.createRole(request.getName(), request.getDescription(), request.getPrivilegeIds()));
    }

    @PutMapping("/{id}/privileges")
    @PreAuthorize("hasAuthority('MANAGE_ROLES') or hasRole('ADMIN')")
    public ResponseEntity<Role> updateRolePrivileges(@PathVariable Long id, @RequestBody List<Long> privilegeIds) {
        return ResponseEntity.ok(roleService.updateRolePrivileges(id, privilegeIds));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE_ROLES') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}

@Data
class RoleRequest {
    private String name;
    private String description;
    private List<Long> privilegeIds;
}
