package com.example.ecommerce.dto.responseDto;

import com.example.ecommerce.Enum.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponse {

    String productName;

    String sellerName;

    Integer quantity;

    Status status;
}
