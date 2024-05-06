package com.example.chatbotproject.controller;

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

    @GetMapping("/travel")
    public String getPackage() throws InterruptedException {

        return travelService.getPackage();
    }

}
