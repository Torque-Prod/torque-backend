package com.example.demo.service;

import com.example.demo.entity.Privilege;
import com.example.demo.entity.Role;
import com.example.demo.repository.PrivilegeRepository;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public List<Privilege> getAllPrivileges() {
        return privilegeRepository.findAll();
    }

    public Role createRole(String name, String description, List<Long> privilegeIds) {
        Set<Privilege> privileges = privilegeRepository.findAllById(privilegeIds).stream().collect(Collectors.toSet());
        Role role = Role.builder()
                .name(name)
                .description(description)
                .privileges(privileges)
                .build();
        return roleRepository.save(role);
    }

    public Role updateRolePrivileges(Long roleId, List<Long> privilegeIds) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        Set<Privilege> privileges = privilegeRepository.findAllById(privilegeIds).stream().collect(Collectors.toSet());
        role.setPrivileges(privileges);
        return roleRepository.save(role);
    }

    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
