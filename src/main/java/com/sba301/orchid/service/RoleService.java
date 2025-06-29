package com.sba301.orchid.service;

import com.sba301.orchid.pojo.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    Role updateRole(Role role);
    void deleteRole(String id);
    Role getRoleById(String id);
    List<Role> getAllRoles();
}
