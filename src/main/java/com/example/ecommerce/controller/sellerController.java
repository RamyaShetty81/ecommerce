package com.example.ecommerce.controller;

import com.example.ecommerce.dto.requestDto.SellerRequest;
import com.example.ecommerce.dto.responseDto.SellerResponse;
import com.example.ecommerce.service.implementation.SellerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class sellerController {

    @Autowired
    SellerServiceImplementation sellerServiceImplementation;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequest sellerRequest) {
        try {
            SellerResponse sellerResponse = sellerServiceImplementation.addSeller(sellerRequest);
            return new ResponseEntity(sellerResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-by -emailId")
    public ResponseEntity getSellerByEmailId(@RequestParam String emailId) {
        SellerResponse sellerResponse;
        try {
            sellerResponse = sellerServiceImplementation.getSellerByEmailId(emailId);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(sellerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-Id")
    public ResponseEntity getSellerById(@RequestParam Integer id) {
        try {
            SellerResponse sellerResponse = sellerServiceImplementation.getSellerById(id);
            return new ResponseEntity(sellerResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
