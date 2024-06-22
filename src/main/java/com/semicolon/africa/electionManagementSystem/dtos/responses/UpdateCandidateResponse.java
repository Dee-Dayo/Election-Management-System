package com.semicolon.africa.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCandidateResponse {
    private Long id;
    private String email;
    private String phoneNumber;

}
