package com.example.ecommerce.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponse {

    Integer noOfItems;

    String customerName;

    Integer cartTotal;

    List<ItemResponse> items;
}
