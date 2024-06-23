package com.semicolon.africa.electionManagementSystem.repositories;

import com.semicolon.africa.electionManagementSystem.models.PartyAffiliation;
import com.semicolon.africa.electionManagementSystem.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT COUNT(v) FROM Vote v WHERE v.election.electionId = :electionId AND v.affiliation = :affiliation")
    Long countVoteByAffiliation(Long electionId, PartyAffiliation affiliation);
    @Query("SELECT COUNT(v) FROM Vote v WHERE v.election.electionId =:electionId")
    Long countVote(Long electionId);
    @Query("SELECT vo FROM Vote vo WHERE vo.election.electionId=:electionId ")
    List<Vote> findByElectionId(Long electionId);
}
