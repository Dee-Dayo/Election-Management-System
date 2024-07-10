package com.semicolon.africa.electionManagementSystem.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RescheduleElectionRequest {
    private Long electionId;
    private Long reSchedulerId;
    private String updateStartTime;
    private String updateEndTime;
}
