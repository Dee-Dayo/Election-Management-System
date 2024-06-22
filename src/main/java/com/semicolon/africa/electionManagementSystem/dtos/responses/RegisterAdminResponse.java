package com.semicolon.africa.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAdminResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String messsage;
}
