package com.example.demoauth.service;


import com.example.demoauth.model.repository.CardRepository;
import com.example.demoauth.model.entity.Card;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;



@Service
public class CardService extends LoggableEntityService<Card, Long> {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository, ObjectMapper objectMapper) {
        super(cardRepository, objectMapper);
        this.cardRepository = cardRepository;
    }
}



