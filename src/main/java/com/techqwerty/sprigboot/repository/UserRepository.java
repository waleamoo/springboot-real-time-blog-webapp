package com.techqwerty.sprigboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techqwerty.sprigboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
