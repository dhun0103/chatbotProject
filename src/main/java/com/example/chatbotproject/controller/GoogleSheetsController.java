package com.example.chatbotproject.controller;

import com.example.chatbotproject.service.GoogleSheetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GoogleSheetsController {

    private final GoogleSheetsService googleSheetsService;

    @PostMapping("/googlesheets")
    public void getGoogleSheets() throws IOException, InterruptedException {

        googleSheetsService.getCellData();
    }
}
