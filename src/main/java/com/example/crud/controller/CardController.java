package com.example.crud.controller;

import com.example.crud.model.Card;
import com.example.crud.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping()
    public Card add(@RequestBody Card card) {
        return cardService.addCard(card);
    }

    @GetMapping("")
    public List<Card> getAll() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Card getById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        return cardService.deleteCard(id);
    }

}
