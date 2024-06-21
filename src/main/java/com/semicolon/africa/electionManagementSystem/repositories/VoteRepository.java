package com.semicolon.africa.electionManagementSystem.repositories;

import com.semicolon.africa.electionManagementSystem.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
//    Long countVoteForCandidate(Long electionId, Long candidateId);
}
