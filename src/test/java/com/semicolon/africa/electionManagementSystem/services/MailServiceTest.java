package com.semicolon.africa.electionManagementSystem.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql({"/database/data.sql"})
class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    public void testMailIsSentWhenUserRegister(){
        String email = "darhyor2050@gmail.com";
        String result = mailService.sendMail(email);
        assertThat(result).containsIgnoringCase("success");
    }
}