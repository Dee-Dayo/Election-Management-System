package com.semicolon.africa.electionManagementSystem.exceptions;

public class DeniedAccessException extends RuntimeException {
    public DeniedAccessException(String message){
        super(message);
    }
}
