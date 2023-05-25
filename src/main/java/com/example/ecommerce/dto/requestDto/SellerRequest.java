package com.example.ecommerce.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SellerRequest {
    String name;

    Integer age;

    String emailId;

    String mobileNo;
}
