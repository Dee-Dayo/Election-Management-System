package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.SendMailRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.SendMailResponse;

public interface MailService {
    SendMailResponse sendMail(SendMailRequest sendMailRequest);
}
