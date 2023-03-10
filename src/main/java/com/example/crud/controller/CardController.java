package com.example.crud.controller;

import com.example.crud.model.Card;
import com.example.crud.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/products")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping()
    @PreAuthorize("hasAuthority('developers:write')")
    public Card add(@RequestBody Card card) {
        return cardService.addCard(card);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('developers:read')")
    public List<Card> getAll() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:read')")
    public Card getById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String deleteById(@PathVariable Long id) {
        return cardService.deleteCard(id);
    }

}
