package com.example.chatbotproject.controller;

import com.example.chatbotproject.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/email")
    public ResponseEntity sendPasswordMail() {

        return emailService.sendMail();
    }
}
