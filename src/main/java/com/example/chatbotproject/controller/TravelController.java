package com.example.chatbotproject.controller;

import com.example.chatbotproject.domain.Travel;
import com.example.chatbotproject.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TravelController {

    private final TravelService travelService;
    private final EmailController emailController;

    @GetMapping("/travel")
    public void getPackage(Travel travel) throws InterruptedException {

        travelService.getPackage(travel);
    }
}
