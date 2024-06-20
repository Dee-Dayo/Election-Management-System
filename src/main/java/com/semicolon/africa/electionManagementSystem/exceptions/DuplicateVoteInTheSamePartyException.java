package com.semicolon.africa.electionManagementSystem.exceptions;

public class DuplicateVoteInTheSamePartyException extends ElectionManagementSystemException {
    public DuplicateVoteInTheSamePartyException(String message){
        super(message);
    }
}
