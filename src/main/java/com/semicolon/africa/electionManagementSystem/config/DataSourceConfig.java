package com.semicolon.africa.electionManagementSystem.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(){
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setJdbcUrl("jdbc:postgresql://localhost/election_management_system");
            dataSource.setUsername("postgres");
            dataSource.setPassword("SQLpassword");
            return dataSource;
    }
}
