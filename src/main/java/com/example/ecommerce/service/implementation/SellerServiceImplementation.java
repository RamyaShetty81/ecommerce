package com.example.ecommerce.service.implementation;

import com.example.ecommerce.dto.requestDto.SellerRequest;
import com.example.ecommerce.dto.responseDto.SellerResponse;
import com.example.ecommerce.exception.EmailAlreadyExistsException;
import com.example.ecommerce.exception.EmailIdNotPresentException;
import com.example.ecommerce.exception.IdNotPresentException;
import com.example.ecommerce.model.Seller;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.service.SellerService;
import com.example.ecommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SellerServiceImplementation implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponse addSeller(SellerRequest sellerRequest) throws EmailAlreadyExistsException {

        if(sellerRepository.findByEmailId(sellerRequest.getEmailId())!=null)
            throw new EmailAlreadyExistsException("Email Id is already registered");

        Seller seller = SellerTransformer.sellerRequestToSeller(sellerRequest);
        sellerRepository.save(seller);
        SellerResponse sellerResponse = SellerTransformer.sellerToSellerResponse(seller);
        return sellerResponse;

    }

    @Override
    public SellerResponse getSellerByEmailId(String emailId) throws EmailIdNotPresentException {
        Seller seller = sellerRepository.findByEmailId(emailId);
        SellerResponse sellerResponse;
        try {
            sellerResponse = SellerTransformer.sellerToSellerResponse(seller);
        }
        catch (Exception e)
        {
            throw new EmailIdNotPresentException("Invalid Email ID!!!");
        }
        return sellerResponse;
    }


    @Override
    public SellerResponse getSellerById(Integer id) throws IdNotPresentException {
        Seller seller = sellerRepository.findById(id).get();
        SellerResponse sellerResponse;
        try {
           sellerResponse = SellerTransformer.sellerToSellerResponse(seller);
        }
        catch (Exception e)
        {
            throw new IdNotPresentException("Invalid ID!!!");
        }
        return sellerResponse;

    }
}
