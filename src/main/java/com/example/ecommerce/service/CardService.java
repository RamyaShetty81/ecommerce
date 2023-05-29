package com.example.ecommerce.service;

import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.dto.requestDto.CardRequest;
import com.example.ecommerce.dto.responseDto.CardResponse;
import com.example.ecommerce.exception.CustomerNotExistException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface CardService {

    public CardResponse addCard(CardRequest cardRequest) throws CustomerNotExistException;

    public List<CardResponse> getAllVisaCards() throws Exception;

    public List<CardResponse> getAllMasterCardsExpiryLessThanGivenDate(Date date);

    public String maxNoOfCards();
}
