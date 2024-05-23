package com.example.chatbotproject.repository;

import com.example.chatbotproject.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
