package com.semicolon.africa.electionManagementSystem.exceptions;

public class VoterAlreadyExistException extends RuntimeException {
    public VoterAlreadyExistException(String message) {
        super(message);
    }
}
