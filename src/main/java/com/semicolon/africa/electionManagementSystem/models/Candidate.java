package com.semicolon.africa.electionManagementSystem.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;


@Getter
@Table(name ="candidates")
@Setter
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private  Long candidateId;
    private String firstName;
    private String lastName;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(value = STRING)
    private Category positionContested;
    @Enumerated(value = STRING)
    private PartyAffiliation partyAffiliation;
    @ManyToOne
    @JoinColumn(name="election_id")
    private Election election;
    @Setter(AccessLevel.NONE)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeCreated;
    @Setter(AccessLevel.NONE)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime timeUpdated;

    @PrePersist
    private void setTimeCreated() {
        timeCreated = now();
    }
    @PreUpdate
    private void setTimeUpdated() {
        timeUpdated = now();
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }

}

