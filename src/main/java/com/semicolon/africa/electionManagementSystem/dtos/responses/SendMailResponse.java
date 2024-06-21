package com.semicolon.africa.electionManagementSystem.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SendMailResponse {
    private int statusCode;
    private String messageId;
}
