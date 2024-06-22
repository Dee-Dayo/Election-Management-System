package com.semicolon.africa.electionManagementSystem.dtos.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAdminRequest {
    private String firstName;
    private String lastName;
    private String email;
}
