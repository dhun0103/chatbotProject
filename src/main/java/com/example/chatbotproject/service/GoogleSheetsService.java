package com.example.chatbotproject.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class GoogleSheetsService {

    private static final String APPLICATION_NAME = "Google Sheets Example";
    private static final String CREDENTIALS_FILE_PATH = "googlesheet/travel-chatbot-423600-c4dd23c0e513.json";
    private static String SPREADSHEET_ID = "1fMS1FTQ0AeneF8NfGUjoCIWCgBwnwdjao5GsGrIrhOA";
    private static Sheets sheetsService;

    private static Sheets getSheetsService() throws IOException {
        if (sheetsService == null) {
            GoogleCredential credential = GoogleCredential.fromStream(new ClassPathResource(CREDENTIALS_FILE_PATH).getInputStream())
                    .createScoped(Collections.singletonList(SheetsScopes.SPREADSHEETS));
            sheetsService = new Sheets.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        }
        return sheetsService;
    }

    public String getCellData() throws IOException {

        String range = "A1:E3";

        Sheets service = getSheetsService();
        ValueRange response = service.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();

        return response.getValues().toString();

//        List<List<Object>> values = response.getValues();
//        if (values == null || values.isEmpty()) {
//            return "No data found.";
//        } else {
//            return values.get(0).get(0).toString(); // Assumes you want the first cell in the first row/column
//        }
    }
}
