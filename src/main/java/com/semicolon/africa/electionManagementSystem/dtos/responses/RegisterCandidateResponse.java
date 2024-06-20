package com.semicolon.africa.electionManagementSystem.dtos.responses;

import com.semicolon.africa.electionManagementSystem.models.Category;
import com.semicolon.africa.electionManagementSystem.models.PartyAffiliation;
import com.semicolon.africa.electionManagementSystem.models.Schedule;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterCandidateResponse {
    private Long candidateId;
    private String firstName;
    private String lastName;
    private PartyAffiliation partyAffiliation;
    private String electionTitle;
    private Category category;
    private Schedule schedule;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String message;
}
