package com.sba301.orchid.repository;

import com.sba301.orchid.pojo.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRoleName(String roleName);
}