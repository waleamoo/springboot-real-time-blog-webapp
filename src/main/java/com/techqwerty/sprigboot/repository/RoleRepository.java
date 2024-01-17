package com.techqwerty.sprigboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techqwerty.sprigboot.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
