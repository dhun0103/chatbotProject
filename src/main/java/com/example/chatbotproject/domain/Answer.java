package com.example.chatbotproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String timeStamp;
    @Column(nullable = false)
    private String travelExperience;

    private String travelDisappointment;

    private String travelMemorable;
    @Column(nullable = false)
    private String travelCompanion;
    @Column(nullable = false)
    private String travelPurpose;
    @Column(nullable = false)
    private String freeTime;

    private String freeTimePlace;

    @Column(nullable = false)
    private String schedule;
    @Column(nullable = false)
    private String accommodationQuestion;

    private String place;

    private String facility;

    private String service;

    private String price;
}
