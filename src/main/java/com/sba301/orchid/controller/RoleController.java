package com.sba301.orchid.controller;

import com.sba301.orchid.pojo.Role;
import com.sba301.orchid.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(String id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(Role role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }
}
