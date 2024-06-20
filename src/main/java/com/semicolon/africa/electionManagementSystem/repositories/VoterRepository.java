package com.semicolon.africa.electionManagementSystem.repositories;

import com.semicolon.africa.electionManagementSystem.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface VoterRepository extends JpaRepository<Voter, Long> {

<<<<<<< HEAD
    Voter findByEmail(String email);
=======
    Voter findByEmail(String username);
>>>>>>> 17640c2c16a396191c314785407f65977adbe2e9
}
