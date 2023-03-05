package com.example.crud.service;

import com.example.crud.model.Card;

import java.util.List;

public interface CardService {
    Card addCard(Card card);
    List<Card> getAllCards();
    Card getCardById(Long id);
    String deleteCard(Long id);
}
