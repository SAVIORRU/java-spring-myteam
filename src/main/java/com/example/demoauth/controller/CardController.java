package com.example.demoauth.controller;

import com.example.demoauth.model.entity.Card;
import com.example.demoauth.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CardController extends LoggableEntityController<Card, Long> {

    private final CardService cardService;

    public CardController(CardService cardService){
        super(cardService);
        this.cardService = cardService;
    }

}
