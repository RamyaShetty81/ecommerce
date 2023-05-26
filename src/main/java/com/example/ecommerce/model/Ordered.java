package com.example.ecommerce.model;

import com.example.ecommerce.Enum.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String orderNo;

    Status status;

    @CreationTimestamp
    Date orderDate;

    Integer totalValue;

    String cardUsed;

    //mapping
    @OneToMany(mappedBy = "ordered", cascade = CascadeType.ALL)
    List<Item> items  = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Customer customer;

}
