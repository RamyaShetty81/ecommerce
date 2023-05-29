package com.example.ecommerce.service;

import com.example.ecommerce.dto.requestDto.CustomerRequest;
import com.example.ecommerce.dto.responseDto.CustomerResponse;
import com.example.ecommerce.exception.EmailIdNotPresentException;
import com.example.ecommerce.exception.IdNotPresentException;
import com.example.ecommerce.exception.MobileNoAlreadyPresentException;
import com.example.ecommerce.exception.NoCustomerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    public CustomerResponse addCustomer(CustomerRequest customerRequest) throws MobileNoAlreadyPresentException;



    CustomerResponse getCustomerByEmail(String email) throws EmailIdNotPresentException;

    CustomerResponse getCustomerById(Integer id) throws IdNotPresentException;

    List<CustomerResponse> getAllCustomers() throws NoCustomerException;

    String updateCustomerByEmail(CustomerRequest customerRequest) throws EmailIdNotPresentException;

    String deleteCustomerByEmail(String email) throws EmailIdNotPresentException;

    String deleteCustomerById(Integer id) throws IdNotPresentException;

    List<CustomerResponse> getCustomersByAge(Integer age) throws NoCustomerException;



}
