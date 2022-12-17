package com.spring.priceGeneratorApp.repository;

import com.spring.priceGeneratorApp.model.Role;
import com.spring.priceGeneratorApp.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleType(RoleType roleType);
}
