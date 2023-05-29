package com.example.ecommerce.service.implementation;

import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.dto.requestDto.CardRequest;
import com.example.ecommerce.dto.responseDto.CardResponse;
import com.example.ecommerce.dto.responseDto.ProductResponse;
import com.example.ecommerce.exception.CustomerNotExistException;
import com.example.ecommerce.model.Card;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CardRepository;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.service.CardService;
import com.example.ecommerce.transformer.CardTransformer;
import com.example.ecommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardServiceImplementation implements CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;
    public CardResponse addCard(CardRequest cardRequest) throws CustomerNotExistException {

        Customer customer = customerRepository.findByMobileNo(cardRequest.getMobileNo());
        if(customer.equals(null))
            throw new CustomerNotExistException("Customer Not Found");

        Card card = CardTransformer.cardRequstToCard(cardRequest);
        card.setCustomer(customer);


        customer.getCards().add(card);
        customerRepository.save(customer);

        return CardTransformer.cardToCardResponse(card);
    }

    @Override
    public List<CardResponse> getAllVisaCards() throws Exception {
        List<Card> cards = cardRepository.findByCardType(CardType.VISA);

        List<CardResponse> cardResponses = cardListToCardResponseList(cards);

        if(cardResponses.size()==0)
            throw new Exception("No cards available in Cardtype Visa");
        return cardResponses;
    }

    @Override
    public List<CardResponse> getAllMasterCardsExpiryLessThanGivenDate(Date date) {
        List<Card> cards = cardRepository.findByCardType(CardType.MASTERCARD);

        List<CardResponse> cardResponses = new ArrayList<>();
        for (Card card : cards) {
            boolean b = (card.getExpiryDate()).compareTo(date) < 1;
            if(b) {
                cardResponses.add(CardTransformer.cardToCardResponse(card));
            }
        }
        return cardResponses;

    }

    @Override
    public String maxNoOfCards() {
        Object[] result = cardRepository.findCardTypeWithMaxCards();
        if(result!=null && result.length>0)
        {
            String cardType = result[0].toString();
            return cardType;
        }
        return "";
    }

    public List<CardResponse> cardListToCardResponseList(List<Card> cards)
    {

        List<CardResponse> cardResponses = new ArrayList<>();
        for (Card card : cards) {
            cardResponses.add(CardTransformer.cardToCardResponse(card));
        }
        return cardResponses;

    }
}
