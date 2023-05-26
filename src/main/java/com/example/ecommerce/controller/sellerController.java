package com.example.ecommerce.controller;

import com.example.ecommerce.dto.requestDto.SellerRequest;
import com.example.ecommerce.dto.responseDto.SellerResponse;
import com.example.ecommerce.exception.EmailIdNotPresentException;
import com.example.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class sellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequest sellerRequest) {
        try {
            SellerResponse sellerResponse = sellerService.addSeller(sellerRequest);
            return new ResponseEntity(sellerResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-by -emailId")
    public ResponseEntity getSellerByEmailId(@RequestParam(name = "email-id") String emailId) {
        SellerResponse sellerResponse;
        try {
            sellerResponse = sellerService.getSellerByEmailId(emailId);
            return new ResponseEntity(sellerResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-by-Id")
    public ResponseEntity getSellerById(@RequestParam(name = "id") Integer id) {
        try {
            SellerResponse sellerResponse = sellerService.getSellerById(id);
            return new ResponseEntity(sellerResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-seller")
    public ResponseEntity getAllSeller()  {
        try {
            List<SellerResponse> sellerResponseList = sellerService.getAllSeller();
            return new ResponseEntity<>(sellerResponseList,HttpStatus.CREATED);
        }catch (Exception e){
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update-by-emailId")
    public ResponseEntity updateByEmailId(@RequestBody SellerRequest sellerRequest)
    {
        String sellerResponse;
        try {
            sellerResponse = sellerService.updateByEmailId(sellerRequest);
            return new ResponseEntity(sellerResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete-by-emailId")
    public ResponseEntity deleteByEmailId(@RequestParam(name = "emailId") String emailId)  {
        try{
            String response = sellerService.deleteByEmailId(emailId);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-by-Id")
    public ResponseEntity deleteById(@RequestParam(name = "id") Integer id)  {
        try{
            String response = sellerService.deleteById(id);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-by-age")
    public ResponseEntity getSellersByAge(Integer age)
    {
        try{
            List<SellerResponse> sellerResponseList = sellerService.getSellersByAge(age);
            return new ResponseEntity<>(sellerResponseList, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }

}
