package com.example.ecommerce.dto.requestDto;

import com.example.ecommerce.Enum.Category;
import com.example.ecommerce.Enum.Status;
import com.example.ecommerce.model.Item;
import com.example.ecommerce.model.Seller;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequest {

    String sellerId;

    String productName;

    Integer quantity;

    Integer price;

    Category category;

}
