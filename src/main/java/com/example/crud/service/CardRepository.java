package com.example.crud.service;

import com.example.crud.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card,Long> {

}
