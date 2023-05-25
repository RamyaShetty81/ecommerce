package com.example.ecommerce.transformer;

import com.example.ecommerce.dto.requestDto.SellerRequest;
import com.example.ecommerce.dto.responseDto.SellerResponse;
import com.example.ecommerce.model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerTransformer {
    public static Seller sellerRequestToSeller(SellerRequest sellerRequest)
    {
        return Seller.builder()
                .name(sellerRequest.getName())
                .age(sellerRequest.getAge())
                .emailId(sellerRequest.getEmailId())
                .mobileNo(sellerRequest.getMobileNo())
                .build();

    }

    public static   SellerResponse sellerToSellerResponse(Seller seller)
    {
        return SellerResponse.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .build();

    }
}

