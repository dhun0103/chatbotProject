package com.example.chatbotproject.repository;

import com.example.chatbotproject.domain.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    Optional<Travel> findByTimeStamp(String timeStamp);
}
