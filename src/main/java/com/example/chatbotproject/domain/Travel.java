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
    private String packageName;
    @Column(nullable = false)
    private String duration;
    @Column(nullable = false)
    private String departureFrom;
    @Column(nullable = false)
    private String airline;
    @Column(nullable = false)
    private String details;
    @Column(nullable = false)
    private String priceNew;
    @Column(nullable = false)
    private String priceOld;


}
