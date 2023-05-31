package com.example.ecommerce.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponse {

    String orderNo;

    Integer totalValue;

    Date orderDate;

    String cardUsed;

    List<ItemResponse > items;

    String customerName;
}
