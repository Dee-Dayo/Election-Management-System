package com.semicolon.africa.electionManagementSystem.controllers;

import com.semicolon.africa.electionManagementSystem.dtos.requests.RegisterCandidateRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.RegisterCandidateResponse;
import com.semicolon.africa.electionManagementSystem.exceptions.ElectionManagementSystemException;
import com.semicolon.africa.electionManagementSystem.services.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("candidate")
public class ElectionCandidateController {
    private CandidateService electionCandidateService;

    @PostMapping("/register")
    public ResponseEntity<?> RegisterCandidateWith(@RequestBody RegisterCandidateRequest registerCandidateRequest){
        try{
            RegisterCandidateResponse response = electionCandidateService.registerCandidateWith(registerCandidateRequest);
            return ResponseEntity.status(CREATED).body(response);
        } catch (ElectionManagementSystemException message){
            return ResponseEntity.status(BAD_REQUEST).body(message.getMessage());
        }
    }


}
