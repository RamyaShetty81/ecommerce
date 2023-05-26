package com.example.ecommerce.service.implementation;

import com.example.ecommerce.dto.requestDto.SellerRequest;
import com.example.ecommerce.dto.responseDto.SellerResponse;
import com.example.ecommerce.exception.EmailAlreadyExistsException;
import com.example.ecommerce.exception.EmailIdNotPresentException;
import com.example.ecommerce.exception.IdNotPresentException;
import com.example.ecommerce.exception.NoSellerException;
import com.example.ecommerce.model.Seller;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.service.SellerService;
import com.example.ecommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
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
            return sellerResponse;
        }
        catch (Exception e)
        {
            throw new EmailIdNotPresentException("Invalid Email ID!!!");
        }

    }


    @Override
    public SellerResponse getSellerById(Integer id) throws IdNotPresentException {
        Seller seller = sellerRepository.findById(id).get();
        SellerResponse sellerResponse;
        try {
           sellerResponse = SellerTransformer.sellerToSellerResponse(seller);
            return sellerResponse;
        }
        catch (Exception e)
        {
            throw new IdNotPresentException("Invalid ID!!!");
        }


    }

    @Override
    public List<SellerResponse> getAllSeller() throws Exception {
        try {
            List<Seller> sellerList = sellerRepository.findAll();
            List<SellerResponse> sellerResponseList = new ArrayList<>();
            for (Seller seller : sellerList) {
                SellerResponse sellerResponse = SellerTransformer.sellerToSellerResponse(seller);
                sellerResponseList.add(sellerResponse);
            }
            return sellerResponseList;
        }
        catch (Exception e)
        {
            throw new NoSellerException("No seller added yet");
        }
    }

    @Override
    public String updateByEmailId(SellerRequest sellerRequest) throws EmailIdNotPresentException {

        try{
            if(sellerRepository.findByEmailId(sellerRequest.getEmailId())!=null)
            {
                Seller seller = sellerRepository.findByEmailId(sellerRequest.getEmailId());
                seller.setName(sellerRequest.getName());
                seller.setAge(sellerRequest.getAge());
                seller.setMobileNo(sellerRequest.getMobileNo());
                seller.setEmailId(sellerRequest.getEmailId());
                Seller updatedseller =  sellerRepository.save(seller);
            }
            return "Seller info updated successfully";
        }
        catch (Exception e)
        {
            throw new EmailIdNotPresentException("Invalid Email Id!!!");
        }

    }

    @Override
    public String deleteByEmailId(String emailId) throws EmailIdNotPresentException {
        if(sellerRepository.findByEmailId(emailId).equals(null))
            throw new EmailIdNotPresentException("Invalid Email id!!!");

        Seller seller = sellerRepository.findByEmailId(emailId);
        sellerRepository.delete(seller);
        return "Seller deleted successfully";

    }

    @Override
    public String deleteById(Integer id) throws IdNotPresentException {
        if(sellerRepository.findById(id).equals(null))
            throw new IdNotPresentException("Invalid Id!!!");

        Seller seller = sellerRepository.findById(id).get();
        sellerRepository.delete(seller);
        return "Seller deleted successfully";
    }

    @Override
    public List<SellerResponse> getSellersByAge(Integer age) throws NoSellerException {
        List<Seller> sellerList = sellerRepository.findByAgeGreaterThanEqual(age);
        List<SellerResponse> sellerResponseList = new ArrayList<>();
        for(Seller seller : sellerList)
        {
            sellerResponseList.add(SellerTransformer.sellerToSellerResponse(seller));
        }

        if(sellerResponseList.size()==0)
            throw new NoSellerException("No seller present equal to or above" + age);

        return sellerResponseList;

    }


}
