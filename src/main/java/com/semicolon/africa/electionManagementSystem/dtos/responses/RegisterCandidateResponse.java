package com.semicolon.africa.electionManagementSystem.dtos.responses;

import com.semicolon.africa.electionManagementSystem.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterCandidateResponse {
    private Long candidateId;
    private String firstName;
    private String lastName;
    private String candidateParty;
    private Category electionCategory;
    private Long electionId;
    private String message;
}
