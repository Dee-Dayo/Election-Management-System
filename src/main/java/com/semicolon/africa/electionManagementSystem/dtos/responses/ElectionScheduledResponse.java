package com.semicolon.africa.electionManagementSystem.dtos.responses;

import com.semicolon.africa.electionManagementSystem.models.Category;
import com.semicolon.africa.electionManagementSystem.models.Schedule;
import lombok.Data;

@Data
public class ElectionScheduledResponse {
    private Long electionId;
    private Category category;
    private Schedule schedule;
}
