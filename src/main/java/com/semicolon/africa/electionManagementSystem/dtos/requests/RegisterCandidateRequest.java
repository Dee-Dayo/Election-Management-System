package com.semicolon.africa.electionManagementSystem.dtos.requests;

import com.semicolon.africa.electionManagementSystem.models.Category;
import com.semicolon.africa.electionManagementSystem.models.Election;
import com.semicolon.africa.electionManagementSystem.models.PartyAffiliation;
import lombok.Data;

@Data
public class RegisterCandidateRequest {
    private String firstName;
    private String lastName;
    private Category positionContested;
    private PartyAffiliation partyAffiliation;
    private Long electionId;

}
