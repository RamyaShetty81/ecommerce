package com.example.ecommerce.model;

import com.example.ecommerce.Enum.Category;
import com.example.ecommerce.Enum.Status;
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
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int quantity;

    int price;

    @Enumerated(EnumType.STRING)
    Category category;

    @Enumerated(EnumType.STRING)
    Status status;

    //mapping
    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();

}
