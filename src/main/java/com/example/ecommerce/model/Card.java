package com.example.ecommerce.model;
import com.example.ecommerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true,nullable = false)
    String cardNo;

    Integer cvv;

    Date expiryDate;

    @Enumerated(EnumType.STRING)
    CardType cardType;

    //mapping
    @ManyToOne
    @JoinColumn
    Customer customer;

}
