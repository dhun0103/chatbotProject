package com.example.chatbotproject.repository;

import com.example.chatbotproject.domain.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
}
