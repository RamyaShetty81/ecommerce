package com.example.ecommerce.controller;

import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.dto.requestDto.CardRequest;
import com.example.ecommerce.dto.responseDto.CardResponse;
import com.example.ecommerce.dto.responseDto.SellerResponse;
import com.example.ecommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequest cardRequest){

        try {
            CardResponse cardResponse = cardService.addCard(cardRequest);
            return new ResponseEntity(cardResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/allVisaCards")
    public ResponseEntity getAllVisaCards()
    {
        try {
            List<CardResponse> allVisaCards = cardService.getAllVisaCards();
            return new ResponseEntity(allVisaCards, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }    }

    @GetMapping("/all-masterCards-at-givenDate")
    public ResponseEntity getAllMasterCardsExpiryLessThanGivenDate(@RequestParam(name = "date")Date date)
    {
        try {
            List<CardResponse> cardResponses = cardService.getAllMasterCardsExpiryLessThanGivenDate(date);
            return new ResponseEntity(cardResponses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/max-of-cards")
    public String maxNoOfCards(){
        String response = "Maximum number of cards registered of cardtype" + cardService.maxNoOfCards();
        return response;
    }
}
