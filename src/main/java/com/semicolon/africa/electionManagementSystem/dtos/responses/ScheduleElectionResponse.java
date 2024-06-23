package com.semicolon.africa.electionManagementSystem.dtos.responses;

import com.semicolon.africa.electionManagementSystem.models.Admin;
import com.semicolon.africa.electionManagementSystem.models.Category;
import com.semicolon.africa.electionManagementSystem.models.Schedule;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleElectionResponse {
    private Admin scheduler;
    private Long electionId;
    private String electionTitle;
    private Category category;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime timeCreated;
    private Schedule schedule;
    private String message;

}
