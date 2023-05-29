package com.example.ecommerce.repository;

import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.dto.responseDto.CardResponse;
import com.example.ecommerce.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    List<Card> findByCardType(CardType cardType);
    @Query("Select c.cardType , count(c) from Card c group by c.cardType order by count(c) desc")
    Object[] findCardTypeWithMaxCards();
}
