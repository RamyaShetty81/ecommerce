package com.example.ecommerce.dto.requestDto;

import com.example.ecommerce.Enum.CardType;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequest
{
    String MobileNo;

    String cardNo;

    Integer cvv;

    Date expiryDate;

    CardType cardType;
}
