package com.example.ecommerce.service;

import com.example.ecommerce.dto.requestDto.SellerRequest;
import com.example.ecommerce.dto.responseDto.SellerResponse;
import com.example.ecommerce.exception.EmailAlreadyExistsException;
import com.example.ecommerce.exception.EmailIdNotPresentException;
import com.example.ecommerce.exception.IdNotPresentException;

public interface SellerService {

    public SellerResponse addSeller(SellerRequest sellerRequestDto) throws EmailAlreadyExistsException;
    public SellerResponse getSellerByEmailId(String emailId) throws EmailIdNotPresentException;
    public SellerResponse getSellerById(Integer id) throws IdNotPresentException;

}
