package com.semicolon.africa.electionManagementSystem.dtos.requests;

import com.semicolon.africa.electionManagementSystem.models.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleElectionRequest {
    private Long adminId;
    private String electionTitle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Category category;


}
