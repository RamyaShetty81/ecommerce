package com.example.ecommerce.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequest {

    Integer customerId;

    Integer productId;

    Integer requiredQuantity;

    String cardNo;

    Integer cvv;
}
