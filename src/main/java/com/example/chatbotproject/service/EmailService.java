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

        String title = "고객님을 위한 ‘맞춤형 여행 패키지’ 추천 ✅";

        StringBuilder content = new StringBuilder();
        content.append("띵동\uD83D\uDC8C 일본 여행 패키지 추천 도착했습니다 ~ \n" +
                "\n" +
                "안녕하세요. 고객님 \uD83D\uDE0A\n" +
                "패키지 여행 추천 챗봇 ‘유키’입니다. \n" +
                "고객님과의 대화를 통해 파악한 여행 취향과 조건을 바탕으로 \n" +
                "맞춤형 일본 패키지 여행을 안내해드립니다. ✨\n" +
                "\n" +
                "고객님께 추천드리는 패키지 여행 목록은 다음과 같습니다: \n");
        int cnt = 1;
        for (TravelPackage travelPackage : travelPackageList) {
            content.append(cnt).append("번.\n");
            cnt++;

            content.append(travelPackage.getTitle()).append("\n");
            content.append("가격 : ").append(travelPackage.getPrice()).append("\n");
            content.append("링크 : ").append(travelPackage.getLink()).append("\n\n");

            if (cnt == 4) break;
        }
        content.append("고객님의 소중한 의견은 저희 서비스 개선에 큰 도움이 됩니다. \n" +
                "추천받으신 여행 패키지에 대해 간단한 피드백을 부탁드립니다!\n" +
                "폼 링크: \n" +
                "\n" +
                "유키 챗봇을 이용해 주셔서 감사합니다. 즐거운 여행 되시길 바랍니다!\n" +
                "유키 드림");

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
