package com.semicolon.africa.electionManagementSystem.services;

import com.semicolon.africa.electionManagementSystem.utils.config.ApplicationConfig;
import com.semicolon.africa.electionManagementSystem.dtos.requests.SendMailRequest;
import com.semicolon.africa.electionManagementSystem.dtos.responses.SendMailResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{

    private final ApplicationConfig appConfig;
    private final RestTemplate restTemplate;

    @Override
    public SendMailResponse sendMail(SendMailRequest sendMailRequest) {
        HttpHeaders headers = addRequestHeaders();
        RequestEntity<SendMailRequest> requestEntity = new RequestEntity<>(sendMailRequest, headers, POST, URI.create(appConfig.getMailServiceUrl()));

        System.out.println("Request Headers: " + headers);
        System.out.println("Request URL: " + appConfig.getMailServiceUrl());
        System.out.println("Request Headers: " + requestEntity.getHeaders());
        System.out.println("Request URL: " + requestEntity.getUrl());
        System.out.println("Request Body: " + requestEntity.getBody());

        ResponseEntity<SendMailResponse> mailResponse =restTemplate.postForEntity(appConfig.getMailServiceUrl(), requestEntity,SendMailResponse.class);

        System.out.println("Response Status Code: " + mailResponse.getStatusCode());
        System.out.println("Response Body: " + mailResponse.getBody());
        return buildSendMailResponse(mailResponse);
    }

    private HttpHeaders addRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.setAccept(List.of(APPLICATION_JSON));
        headers.set("api-key", appConfig.getMailApiKey());

        System.out.println("Generated Headers: " + headers);
        return headers;
    }

    private static SendMailResponse buildSendMailResponse(ResponseEntity<SendMailResponse> mailResponse) {
        HttpStatusCode code = mailResponse.getStatusCode();
        var response = mailResponse.getBody();
        if (response==null) throw new RuntimeException("Mail Sending failed");
        response.setStatusCode(code.value());
        return response;
    }
}
