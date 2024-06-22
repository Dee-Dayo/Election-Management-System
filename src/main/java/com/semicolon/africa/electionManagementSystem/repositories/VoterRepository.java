package com.semicolon.africa.electionManagementSystem.repositories;

import com.semicolon.africa.electionManagementSystem.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VoterRepository extends JpaRepository<Voter, Long> {
    Voter findByEmail(String username);
}
