package com.example.chatbotproject.service;

import com.example.chatbotproject.domain.EmailMessage;
import com.example.chatbotproject.domain.TravelPackage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(List<TravelPackage> travelPackageList, String email) {

        String title = "일본여행 챗봇 - 패키지 추천";

        StringBuilder content = new StringBuilder();
        int cnt = 0;
        for(TravelPackage travelPackage : travelPackageList){
            content.append(cnt).append("번.\n");
            cnt++;

            content.append(travelPackage.getTitle()).append("\n");
            content.append("가격 : ").append(travelPackage.getPrice()).append("\n");
            content.append("링크 : ").append(travelPackage.getLink()).append("\n\n");
        }

        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject(title)
                .message(content)
                .build();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
            mimeMessageHelper.setText(String.valueOf(emailMessage.getMessage())); // 메일 본문 내용

            javaMailSender.send(mimeMessage);

            log.info("Success");

        } catch (MessagingException e) {
            log.info("fail");
            throw new RuntimeException(e);
        }
    }
}
