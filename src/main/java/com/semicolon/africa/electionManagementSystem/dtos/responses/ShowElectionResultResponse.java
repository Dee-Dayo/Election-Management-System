package com.semicolon.africa.electionManagementSystem.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class ShowElectionResultResponse {
    private Long ElectionId;
    private Map<String, Long> results;




}
