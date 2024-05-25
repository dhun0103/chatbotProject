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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.sql.InFragment.NULL;


@Service
@RequiredArgsConstructor
public class GoogleSheetsService {

    private static final String APPLICATION_NAME = "Google Sheets Example";
    private static final String CREDENTIALS_FILE_PATH = "googlesheet/travel-chatbot-423600-c4dd23c0e513.json";
    @Value("${google.spread.sheet.id}")
    private static String SPREADSHEET_ID;
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

        String range = "시트1!A:W";
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

            // 빈 값 처리를 위한 코드 추가
            String[] columns = new String[23]; // W까지 총 23개의 컬럼
            for (int j = 0; j < columns.length; j++) {
                columns[j] = j < row.size() && row.get(j) != null ? row.get(j).toString() : null;
            }

            String timeStamp = columns[0];
            Optional<Travel> existingTravel = travelRepository.findByTimeStamp(timeStamp);
            if(existingTravel.isPresent()){
                continue;
            }

            Travel travel = new Travel();
            travel.setTimeStamp(columns[0]); // Column A
            travel.setDestination(columns[1]); // Column B
            travel.setTravelMonth(columns[2]); // Column C
            travel.setTravelDay(columns[3]); // Column D
            travel.setAirport(columns[4]); // Column E
            travel.setDuration(columns[8]); // Column I
            travel.setExpense(columns[10]); // Column K
            travel.setTravelPreference(columns[12]); // Column M
            travel.setAccommodation(columns[16]); // Column Q
            travel.setEmail(columns[22]); // Column W
            travelRepository.save(travel);

            Answer answer = new Answer();
            answer.setTimeStamp(columns[0]); // Column A
            answer.setTravelExperience(columns[5]); // Column F
            answer.setTravelDisappointment(columns[6]); // Column G
            answer.setTravelMemorable(columns[7]); // Column H
            answer.setTravelCompanion(columns[9]); // Column J
            answer.setTravelPurpose(columns[11]); // Column L
            answer.setTravelPreference(columns[12]); // Column M
            answer.setFreeTime(columns[13]); // Column N
            answer.setFreeTimePlace(columns[14]); // Column O
            answer.setSchedule(columns[15]); // Column P
            answer.setAccommodationQuestion(columns[17]); // Column R
            answer.setPlace(columns[18]); // Column S
            answer.setFacility(columns[19]); // Column T
            answer.setService(columns[20]); // Column U
            answer.setPrice(columns[21]); // Column V
            answerRepository.save(answer);

            if(columns[22] != null && !columns[22].equals(NULL)){
                travelController.getPackage(travel);
            }
        }
    }
}
