package com.semicolon.africa.electionManagementSystem.repositories;

import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface VoterRepository extends JpaRepository<Voter, Long> {

    Voter findByEmail(String email);

    Long countVoteForCandidate(Long electionId, Long candidateId);

    Long countVote(Long electionId);
}
