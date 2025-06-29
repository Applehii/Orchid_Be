package com.sba301.orchid.service;

import com.sba301.orchid.pojo.Role;
import com.sba301.orchid.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(String id) {
        Role role = roleRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new IllegalArgumentException("Role with ID " + id + " does not exist."));
        roleRepository.delete(role);
    }

    @Override
    public Role getRoleById(String id) {
        return roleRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new IllegalArgumentException("Role with ID " + id + " does not exist."));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
