package com.example.chatbotproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ChatbotProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(ChatbotProjectApplication.class, args);
    }
}
