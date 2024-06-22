package com.semicolon.africa.electionManagementSystem.dtos.responses;

import com.semicolon.africa.electionManagementSystem.models.PartyAffiliation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class ShowElectionResultResponse {
    private Long ElectionId;
<<<<<<< HEAD
    private Map<String, Long> results;

    public ShowElectionResultResponse() {

    }
=======
    private Map<PartyAffiliation, Long> results;
>>>>>>> 23dcecdc50216c6ebb0e470ff6c0c26b6bbc4669
}
