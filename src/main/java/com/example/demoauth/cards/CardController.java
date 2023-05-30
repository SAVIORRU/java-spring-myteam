package com.example.demoauth.cards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CardController {

    @Autowired
    CardRepository cardRepository;

    @GetMapping
    public ResponseEntity<?> getAllCards() {
        Card card = new Card();
        card.setId(1L);
        card.setTitle("My First Card");
        card.setDescription("Description of my first card");

        List<Card> cards = new ArrayList<Card>();
        cards.add(card);

        Card card2 = new Card();
        card2.setId(2L);
        card2.setTitle("My Second Card");
        card2.setDescription("Description of my second card");
        cards.add(card2);

        return ResponseEntity.ok(cards);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCardById(@PathVariable Long id) {

        return ResponseEntity.ok("response: cards/" + id);

/*        Optional<Card> card = cardRepository.findById(id);

        if (card.isPresent()) {
            return ResponseEntity.ok(card.get());
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }
}
