package com.semicolon.africa.electionManagementSystem.exceptions;

public class ElectionNotFoundException extends RuntimeException {
    public ElectionNotFoundException(String message) {
        super(message);
    }
}
