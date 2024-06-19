package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ElectionScheduledResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ScheduleElectionResponse;
import com.semicolon.africa.electionManagementSystem.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.Month;

import static com.semicolon.africa.electionManagementSystem.models.Schedule.SCHEDULED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ElectionManagementServiceTest {
    @Autowired
    private AdminService adminService;


    @Sql(scripts = "/db/data.sql")
    @Test
    public void scheduleElection_ElectionCanBeScheduledTest(){
        ScheduleElectionRequest request = new ScheduleElectionRequest();
        request.setElectionTitle("LGA Election 3");
        request.setStartDate(LocalDateTime.of(2024, Month.JUNE,20,12,0));
        request.setEndDate(LocalDateTime.of(2024, Month.JUNE,21,12,0));
        request.setCategory(Category.NATIONAL);
        ScheduleElectionResponse response = adminService.scheduleElection(request);
        assertThat(response).isNotNull();
        assertThat(response.getSchedule()).isEqualTo(SCHEDULED);
    }

    @Sql(scripts = "/db/data.sql")
    @Test
    public void checkElectionStatus_Test(){

        ElectionScheduledResponse response = adminService.getElectionSchedule(11L);
        assertThat(response).isNotNull();
        assertThat(response.getElectionId()).isEqualTo(11);
        assertThat(response.getSchedule()).isEqualTo(SCHEDULED);
    }

    @Sql(scripts = "/db/data.sql")
    @Test
    public void startElection_ElectionStartsTest(){


    }


}