package com.semicolon.africa.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@Table(name="votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long voteId;
    private PartyAffiliation affiliation;
    @OneToOne
    private Voter voter;
    @ManyToOne
    private Election election;
}
