package com.semicolon.africa.electionManagementSystem.repositories;

import com.semicolon.africa.electionManagementSystem.models.Candidate;
import com.semicolon.africa.electionManagementSystem.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface VoterRepository extends JpaRepository<Voter, Long> {

<<<<<<< HEAD
    Voter findByEmail(String email);
=======
    Voter findByEmail(String username);

    Long countVoteForCandidate(Long electionId, Long candidateId);

    Long countVote(Long electionId);
>>>>>>> e92bd2cbacc5892350636f531552d8247f05b9fc
}
