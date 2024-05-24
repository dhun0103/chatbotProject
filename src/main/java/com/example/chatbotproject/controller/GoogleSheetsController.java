package com.example.chatbotproject.controller;

import com.example.chatbotproject.service.GoogleSheetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GoogleSheetsController {

    private final GoogleSheetsService googleSheetsService;

    @GetMapping("/googlesheets")
    public void getGoogleSheets() throws IOException, InterruptedException {

        googleSheetsService.getCellData();
    }
}
