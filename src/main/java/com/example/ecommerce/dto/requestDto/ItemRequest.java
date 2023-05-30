package com.example.ecommerce.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemRequest {

    Integer requiredQuantity;

    Integer customerId;

    Integer productId;
}
