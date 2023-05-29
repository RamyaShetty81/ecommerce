package com.example.ecommerce.service;

import com.example.ecommerce.dto.requestDto.CustomerRequest;
import com.example.ecommerce.dto.responseDto.CustomerResponse;
import com.example.ecommerce.exception.MobileNoAlreadyPresentException;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    public CustomerResponse addCustomer(CustomerRequest customerRequest) throws MobileNoAlreadyPresentException;



}
