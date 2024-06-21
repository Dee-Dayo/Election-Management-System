package com.semicolon.africa.electionManagementSystem.config;


import com.semicolon.africa.electionManagementSystem.services.UserDetailsServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
@Getter
public class ApplicationConfig {

    @Value("${mail.api.key}")
    private String mailApiKey;
    @Value("${mail.api.url}")
    private String mailServiceUrl;

    private final UserDetailsServiceImpl service;
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> service.loadUserByUsername(username);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
