package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.dtos.requests.Recipient;
import com.semicolon.africa.electionManagementSystem.dtos.requests.SendMailRequest;
import com.semicolon.africa.electionManagementSystem.dtos.requests.Sender;
import com.semicolon.africa.electionManagementSystem.dtos.responses.SendMailResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql({"/database/data.sql"})
class MailServiceTest {
    @Autowired
    private MailService mailService;

     @Test
    public void testSendMail(){
        SendMailRequest mailRequest = buildMailRequest();
        SendMailResponse response = mailService.sendMail(mailRequest);
        assertNotNull(response);
        assertEquals(201, response.getStatusCode());
    }

    private static SendMailRequest buildMailRequest() {
        //1. Create mail sending request
        SendMailRequest mailRequest = new SendMailRequest();
        //2. Create Sender
        Sender sender = new Sender("Election management system", "election@semicolon.africa");
        //3. Create Recipient Collection
        List<Recipient> recipients = List.of(
                new Recipient("dayo", "darhyor2050@gmail.com")
        );
        mailRequest.setSubject("testing 1,2,3...");
        mailRequest.setHtmlContent("<p>Hello Semicolon</p>");
        mailRequest.setSender(sender);
        mailRequest.setRecipients(recipients);
        return mailRequest;
    }
}