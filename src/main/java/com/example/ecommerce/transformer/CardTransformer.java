package com.example.ecommerce.transformer;

import com.example.ecommerce.dto.requestDto.CardRequest;
import com.example.ecommerce.dto.responseDto.CardResponse;
import com.example.ecommerce.model.Card;

public class CardTransformer {
    public static Card cardRequstToCard(CardRequest cardRequest)
    {
        return Card.builder()
                .cardNo(cardRequest.getCardNo())
                .expiryDate(cardRequest.getExpiryDate())
                .cvv(cardRequest.getCvv())
                .cardType(cardRequest.getCardType())
                .build();
    }
    public static CardResponse cardToCardResponse(Card card)
    {
        return CardResponse.builder()
                .cardNo(card.getCardNo())
                .customerName(card.getCustomer().getName())
                .build();
    }
}
