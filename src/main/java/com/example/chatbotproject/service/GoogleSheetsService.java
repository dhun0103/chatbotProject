package com.example.chatbotproject.service;

import com.example.chatbotproject.controller.TravelController;
import com.example.chatbotproject.domain.Answer;
import com.example.chatbotproject.domain.Travel;
import com.example.chatbotproject.repository.AnswerRepository;
import com.example.chatbotproject.repository.TravelRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GoogleSheetsService {

    private static final String APPLICATION_NAME = "Google Sheets Example";
    private static final String CREDENTIALS_FILE_PATH = "googlesheet/travel-chatbot-423600-c4dd23c0e513.json";
    private static String SPREADSHEET_ID = "1fMS1FTQ0AeneF8NfGUjoCIWCgBwnwdjao5GsGrIrhOA";
    private static Sheets sheetsService;
    private final TravelController travelController;
    private final TravelRepository travelRepository;
    private final AnswerRepository answerRepository;



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

    public void getCellData() throws IOException, InterruptedException {

        String range = "Sheet1!A:W";
        Sheets service = getSheetsService();
        ValueRange response = service.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();

        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
            return;
        }

        for (int i = 1; i < values.size(); i++) {
            List<Object> row = values.get(i);

            String timeStamp = (String) row.get(0);
            Optional<Travel> existingTravel = travelRepository.findByTimeStamp(timeStamp);
            if(existingTravel.isPresent()){
                continue;
            }

            Travel travel = new Travel();
            travel.setTimeStamp((String) row.get(0)); // Column A
            travel.setDestination((String) row.get(1)); // Column B
            travel.setTravelMonth((String) row.get(2)); // Column C
            travel.setTravelDay((String) row.get(3)); // Column D
            travel.setAirport((String) row.get(4)); // Column E
            travel.setDuration((String) row.get(8)); // Column I
            travel.setExpense((String) row.get(10)); // Column K
            travel.setTravelPreference((String) row.get(12)); // Column M
            travel.setAccommodation((String) row.get(16)); // Column Q
            travel.setEmail((String) row.get(22)); // Column Q
            travelRepository.save(travel);
            travelController.getPackage(travel);

            Answer answer = new Answer();
            answer.setTimeStamp((String) row.get(0)); // Column A
            answer.setTravelExperience((String) row.get(5)); // Column F
            answer.setTravelDisappointment((String) row.get(6)); // Column G
            answer.setTravelMemorable((String) row.get(7)); // Column H
            answer.setTravelCompanion((String) row.get(9)); // Column J
            answer.setTravelPurpose((String) row.get(11)); // Column L
            answer.setFreeTime((String) row.get(13)); // Column N
            answer.setFreeTimePlace((String) row.get(14)); // Column O
            answer.setSchedule((String) row.get(15)); // Column P
            answer.setAccommodationQuestion((String) row.get(17)); // Column R
            answer.setPlace((String) row.get(18)); // Column S
            answer.setFacility((String) row.get(19)); // Column T
            answer.setService((String) row.get(20)); // Column U
            answer.setPrice((String) row.get(21)); // Column V
            answerRepository.save(answer);
        }
    }
}
