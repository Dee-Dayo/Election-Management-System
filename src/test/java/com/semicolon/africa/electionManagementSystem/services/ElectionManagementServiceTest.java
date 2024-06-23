package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.CancelElectionRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterAdminRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.ScheduleElectionRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.*;
import com.semicolon.africa.electionManagementSystem.exceptions.DeniedAccessException;
import com.semicolon.africa.electionManagementSystem.models.Category;
import com.semicolon.africa.electionManagementSystem.models.Election;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.Month;

import static com.semicolon.africa.electionManagementSystem.models.Category.LGA;
import static com.semicolon.africa.electionManagementSystem.models.Category.NATIONAL;
import static com.semicolon.africa.electionManagementSystem.models.PartyAffiliation.APC;
import static com.semicolon.africa.electionManagementSystem.models.Schedule.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ElectionManagementServiceTest {
    @Autowired
    private AdminService adminService;


    @Sql(scripts = "/adminTestDb/elections.sql")
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

    @Sql(scripts = "/adminTestDb/elections.sql")
    @Test
    public void checkElectionStatus_Test(){

        ElectionScheduledResponse response = adminService.getElectionSchedule(11L);
        assertThat(response).isNotNull();
        assertThat(response.getElectionId()).isEqualTo(11);
        assertThat(response.getSchedule()).isEqualTo(SCHEDULED);
    }

    @Sql(scripts = "/adminTestDb/elections.sql")
    @Test
    public void registerCandidate_CandidateIsRegisteredTest(){
        RegisterCandidateRequest request = new RegisterCandidateRequest();
        request.setFirstName("Dayo");
        request.setLastName("Akinyemi");
        request.setPartyAffiliation(APC);
        request.setElectionId(11L);
        request.setPositionContested(NATIONAL);
        RegisterCandidateResponse response = adminService.registerCandidate(request);
        assertCandidateIsRegistered(response);

    }

    private static void assertCandidateIsRegistered(RegisterCandidateResponse response) {
        assertThat(response).isNotNull();
        assertThat(response.getCandidateId()).isNotNull();
        assertThat(response.getCategory()).isEqualTo(NATIONAL);
        assertThat(response.getFirstName()).isEqualTo("Dayo");
        assertThat(response.getLastName()).isEqualTo("Akinyemi");
        assertThat(response.getPartyAffiliation()).isEqualTo(APC);
        assertThat(response.getMessage()).contains("success");
        assertThat(response.getSchedule()).isNotEqualTo(NOT_SCHEDULED);
        assertThat(response.getSchedule()).isNotEqualTo(ENDED);
        assertThat(response.getElectionTitle()).isEqualTo("National Election 1");
    }

    @Sql(scripts = "/adminTestDb/elections.sql")
    @Test
    public void registerCandidateForUnscheduledElection_ThrowsExceptionTest(){
        RegisterCandidateRequest request = new RegisterCandidateRequest();
        request.setFirstName("Dayo");
        request.setLastName("Akinyemi");
        request.setPartyAffiliation(APC);
        request.setPositionContested(LGA);
        request.setElectionId(11L);
        Election election = adminService.findElectionBy(11L);
        assertThat(election.getCategory()).isEqualTo(NATIONAL);
        assertThrows(DeniedAccessException.class, ()-> adminService.registerCandidate(request));
    }

    @Sql(scripts = "/adminTestDb/elections.sql")
    @Test
    public void cancelScheduledElection_electionIsCancelledTest(){
        CancelElectionRequest request = new CancelElectionRequest();
        request.setAdminId(1L);
        request.setElectionId(11L);
        request.setReason("RIGGED BY CANDIDATES");
        CancelElectionResponse response = adminService.cancelElection(request);
        assertThat(response).isNotNull();
        assertThat(response.getCancellerId()).isEqualTo(1L);
        assertThat(response.getElection().getElectionId()).isEqualTo(11L);
        assertThat(response.getElection().getCategory()).isEqualTo(NATIONAL);
        assertThat(response.getElection().getSchedule()).isEqualTo(CANCELLED);

    }

    @Test
    public  void registerAdmin_AdminIsRegisteredTest(){
        RegisterAdminRequest request = new RegisterAdminRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("johnDoe@email.com");
        RegisterAdminResponse response = adminService.registerAdmin(request);

        assertThat(response).isNotNull();
        assertThat(response.getMesssage()).contains("success");
        assertThat(response.getId()).isNotNull();
    }


}