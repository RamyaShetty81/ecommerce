package com.example.ecommerce.controller;

import com.example.ecommerce.dto.requestDto.CustomerRequest;
import com.example.ecommerce.dto.responseDto.CustomerResponse;
import com.example.ecommerce.exception.EmailIdNotPresentException;
import com.example.ecommerce.exception.IdNotPresentException;
import com.example.ecommerce.exception.NoCustomerException;
import com.example.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest)
    {
        try {
            CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
            return new ResponseEntity(customerResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomerByEmail(@PathVariable String email) {
        try {
            CustomerResponse customerResponse = customerService.getCustomerByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
        } catch (EmailIdNotPresentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Integer id) {
        try {
            CustomerResponse customerResponse = customerService.getCustomerById(id);
            return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
        } catch (IdNotPresentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        try {
            List<CustomerResponse> customerResponses = customerService.getAllCustomers();
            return ResponseEntity.status(HttpStatus.OK).body(customerResponses);
        } catch (NoCustomerException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<String> updateCustomerByEmail(@PathVariable String email,
                                                        @RequestBody CustomerRequest customerRequest) {
        try {
            customerRequest.setEmail(email);
            String message = customerService.updateCustomerByEmail(customerRequest);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (EmailIdNotPresentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomerByEmail(@PathVariable String email) {
        try {
            String message = customerService.deleteCustomerByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (EmailIdNotPresentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Integer id) {
        try {
            String message = customerService.deleteCustomerById(id);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (IdNotPresentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<CustomerResponse>> getCustomersByAge(@PathVariable Integer age) {
        try {
            List<CustomerResponse> customerResponses = customerService.getCustomersByAge(age);
            return ResponseEntity.status(HttpStatus.OK).body(customerResponses);
        } catch (NoCustomerException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }


}
