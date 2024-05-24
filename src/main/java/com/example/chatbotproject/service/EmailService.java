package com.example.chatbotproject.service;

import com.example.chatbotproject.domain.EmailMessage;
import com.example.chatbotproject.domain.TravelPackage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(List<TravelPackage> travelPackageList) {

        String email = "arrrrr180@gmail.com";

        EmailMessage emailMessage = EmailMessage.builder()
                .to(email)
                .subject("여행 패키지 추천")
                .message("도쿄로 떠나세요~")
                .build();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
            mimeMessageHelper.setText(emailMessage.getMessage()); // 메일 본문 내용

            javaMailSender.send(mimeMessage);

            log.info("Success");

            return;

        } catch (MessagingException e) {
            log.info("fail");
            throw new RuntimeException(e);
        }
    }
}
