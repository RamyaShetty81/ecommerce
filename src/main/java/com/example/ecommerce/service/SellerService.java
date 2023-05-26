package com.example.ecommerce.service;

import com.example.ecommerce.dto.requestDto.SellerRequest;
import com.example.ecommerce.dto.responseDto.SellerResponse;
import com.example.ecommerce.exception.EmailAlreadyExistsException;
import com.example.ecommerce.exception.EmailIdNotPresentException;
import com.example.ecommerce.exception.IdNotPresentException;
import com.example.ecommerce.exception.NoSellerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellerService {

    public SellerResponse addSeller(SellerRequest sellerRequest) throws EmailAlreadyExistsException;

    public SellerResponse getSellerByEmailId(String emailId) throws EmailIdNotPresentException;

    public SellerResponse getSellerById(Integer id) throws IdNotPresentException;

    List<SellerResponse> getAllSeller() throws Exception;

    String updateByEmailId(SellerRequest sellerRequest) throws EmailIdNotPresentException;


    String deleteByEmailId(String emailId) throws EmailIdNotPresentException;

    String deleteById(Integer id) throws IdNotPresentException;

    List<SellerResponse> getSellersByAge(Integer age) throws NoSellerException;
}
