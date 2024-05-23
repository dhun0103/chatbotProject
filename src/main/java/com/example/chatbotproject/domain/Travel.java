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

    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private String travelMonth;
    @Column(nullable = false)
    private String travelDay;
    @Column(nullable = false)
    private String airport;
    @Column(nullable = false)
    private String duration;
    @Column(nullable = false)
    private String expense;
    @Column(nullable = false)
    private String travelPreference;
    @Column(nullable = false)
    private String accommodation;
    @Column(nullable = false)
    private String email;
}
