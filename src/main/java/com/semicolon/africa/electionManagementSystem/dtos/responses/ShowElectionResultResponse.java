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
    private Map<PartyAffiliation, Long> results;
}
