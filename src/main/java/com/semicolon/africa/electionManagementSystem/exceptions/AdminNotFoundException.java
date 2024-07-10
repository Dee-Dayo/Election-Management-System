package com.semicolon.africa.electionManagementSystem.exceptions;

import com.semicolon.africa.electionManagementSystem.services.ElectionManagementService;

public class AdminNotFoundException extends ElectionManagementSystemException {
    public AdminNotFoundException(String message) {
        super(message);
    }
}
