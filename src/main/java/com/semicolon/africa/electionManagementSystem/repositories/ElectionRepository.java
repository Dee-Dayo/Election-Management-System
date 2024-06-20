package com.semicolon.africa.electionManagementSystem.repositories;

import com.semicolon.africa.electionManagementSystem.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election, Long> {
    Election getElectionByElectionId(Long electionId);
}
