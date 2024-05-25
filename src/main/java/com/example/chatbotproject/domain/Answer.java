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

    @Column(nullable = false, name = "1.타임스탬프")
    private String timeStamp;
    @Column(name = "2-1.여행경험유무")
    private String travelExperience;
    @Column(name = "2-2.여행아쉬웠던점")
    private String travelDisappointment;
    @Column(name = "2-3.여행기억에남는순간")
    private String travelMemorable;
    @Column(name = "3.여행동반자")
    private String travelCompanion;
    @Column(name = "4.여행목적")
    private String travelPurpose;
    @Column(name = "5.목적지")
    private String travelPreference;
    @Column(name = "6-1.자유시간유무")
    private String freeTime;
    @Column(name = "6-2.자유시간가고싶은곳")
    private String freeTimePlace;
    @Column(name = "7.스케쥴")
    private String schedule;
    @Column(name = "8-1.숙소질문")
    private String accommodationQuestion;
    @Column(name = "8-2.숙소위치")
    private String place;
    @Column(name = "8-3.숙소시설")
    private String facility;
    @Column(name = "8-4.숙소서비스")
    private String service;
    @Column(name = "8-5.숙소가격")
    private String price;
}
