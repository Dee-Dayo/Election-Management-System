package com.semicolon.africa.electionManagementSystem.controllers;

import com.semicolon.africa.electionManagementSystem.ElectionManagementSystemApplication;
import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.dtos.responses.ShowElectionResultResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.services.ElectionCandidateService;
import com.semicolon.africa.electionManagementSystem.services.ElectionManagementService;
import com.sun.net.httpserver.Authenticator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("candidate")
public class ElectionCandidateController {
    private ElectionCandidateService electionCandidateService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCandidateWith(@RequestBody RegisterCandidateRequest registerCandidateRequest){
        try{
            RegisterCandidateResponse response = electionCandidateService.registerCandidateWith(registerCandidateRequest);
            return ResponseEntity.status(CREATED).body(response);
        } catch (ElectionManagementSystemException message){
            return ResponseEntity.status(BAD_REQUEST).body(message.getMessage());
        }
    }
    @GetMapping("/view")
    public ResponseEntity<?> viewElectionResultFor(@RequestBody long electionId){
        try{
            ShowElectionResultResponse response = electionCandidateService.viewElectionResultFor(electionId);
            return ResponseEntity.status(CREATED).body(response);
        }catch(ElectionManagementSystemException message){
            return ResponseEntity.status(BAD_REQUEST).body(message.getMessage());
        }
    }


}
