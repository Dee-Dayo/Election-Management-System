package com.semicolon.africa.electionManagementSystem.repositories;

import com.semicolon.africa.electionManagementSystem.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String username);
}
