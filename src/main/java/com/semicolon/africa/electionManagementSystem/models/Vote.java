package com.semicolon.africa.electionManagementSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
@Table(name="votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;
    @OneToOne
    private Candidate candidate;
    @OneToOne
    private Voter voter;
    @ManyToOne
    private Election election;
}
