package com.example.ecommerce.dto.requestDto;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequest {
    String name;

    Integer age;

    String email;

    String mobileNo;

    String address;
}
