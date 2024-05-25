package com.example.chatbotproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "1.타임스탬프")
    private String timeStamp;
    @Column(name = "2.목적지")
    private String destination;
    @Column(name = "3-1.몇월")
    private String travelMonth;
    @Column(name = "3-2.며칠")
    private String travelDay;
    @Column(name = "4.출발공항")
    private String airport;
    @Column(name = "5.여행기간")
    private String duration;
    @Column(name = "6.여행경비")
    private String expense;
    @Column(name = "7.여행취향")
    private String travelPreference;
    @Column(name = "8.숙소퀄리티")
    private String accommodation;
    @Column(name = "9.이메일")
    private String email;
}
