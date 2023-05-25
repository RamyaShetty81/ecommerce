package com.example.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    Integer age;

    @Column(unique = true)
    String emailId;

    String mobileNo;

    //mapping

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();

}
