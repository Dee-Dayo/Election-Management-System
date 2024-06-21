package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.AddVoteRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.CountVoteForPartyRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.AddVoteResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.VoteNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static com.semicolon.africa.electionManagementSystem.models.PartyAffiliation.APC;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Sql("/database/data.sql")
public class VoteServiceImplTest {
    @Autowired
    private VoteService voteService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private VoterService voterService;


    @Test
    public void addVote() {
        AddVoteRequest request = addVoteRequest();

        AddVoteResponse addVoteResponse = voteService.addVote(request,adminService,voterService);
        assertThat(addVoteResponse).isNotNull();
    }

    private static AddVoteRequest addVoteRequest() {
        AddVoteRequest request = new AddVoteRequest();
        request.setElectionId(200L);
        request.setAffiliation(APC);
        request.setVoterId(202L);
        return request;
    }

    @Test
    public void countElectionVote() {
        assertThat(voteService.countElectionVote(200L)).isEqualTo(8);
    }

    @Test
    public void countVoteForParties() {
        CountVoteForPartyRequest countVoteForPartyRequest = new CountVoteForPartyRequest();
        countVoteForPartyRequest.setAffiliation(APC);
        countVoteForPartyRequest.setElectionId(200L);
        assertThat(voteService.countVoteForParties(countVoteForPartyRequest)).isEqualTo(4);
    }


    @Test
    void showResult() throws VoteNotFoundException {
        assertThat(voteService.showResult(200L)).isNotNull();
        assertThat(voteService.showResult(200L).getResults().size()).isEqualTo(4);
        assertThat(voteService.showResult(200L).getResults().get(APC)).isEqualTo(4);
    }

}