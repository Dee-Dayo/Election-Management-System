package com.semicolon.africa.electionManagementSystem.dtos.requests;

import com.semicolon.africa.electionManagementSystem.models.PartyAffiliation;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountVoteForPartyRequest {
    private PartyAffiliation affiliation;
    private Long ElectionId;
}
