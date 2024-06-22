package com.semicolon.africa.electionManagementSystem.dtos.responses;

import com.semicolon.africa.electionManagementSystem.models.Admin;
import com.semicolon.africa.electionManagementSystem.models.Category;
import com.semicolon.africa.electionManagementSystem.models.Schedule;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleElectionResponse {
    private Admin scheduler;
    private Long electionId;
    private Category category;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime timeCreated;
    private Schedule schedule;

}
