package com.example.chatbotproject.controller;

import com.example.chatbotproject.domain.TravelPackage;
import com.example.chatbotproject.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/email")
    public void sendPasswordMail(List<TravelPackage> travelPackageList) {

        emailService.sendMail(travelPackageList);
    }
}
