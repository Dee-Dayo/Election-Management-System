package com.semicolon.africa.electionManagementSystem.repositories;

import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Query("SELECT c FROM Candidate c WHERE c.election.electionId=:electionId ")
    List<Candidate> findByElectionId(Long electionId);
}
