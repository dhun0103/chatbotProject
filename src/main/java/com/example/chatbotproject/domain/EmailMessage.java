package com.example.chatbotproject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.attribute.FileTime;

@Getter
@Setter
@Builder
public class EmailMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String to;
    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private String message;
}
