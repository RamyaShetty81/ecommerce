package com.example.ecommerce.transformer;

import com.example.ecommerce.dto.requestDto.CustomerRequest;
import com.example.ecommerce.dto.responseDto.CustomerResponse;
import com.example.ecommerce.model.Customer;

public class CustomerTransformer {

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest)
    {
        return Customer.builder()
                .name(customerRequest.getName())
                .age(customerRequest.getAge())
                .mobileNo(customerRequest.getMobileNo())
                .email(customerRequest.getEmail())
                .address(customerRequest.getAddress())
                .build();

    }

    public static CustomerResponse customerToCustomerResponse(Customer customer)
    {
        return CustomerResponse.builder()
                .name(customer.getName())
                .message("Welcome "+ customer.getName() + " to AMAZOFF")
                .build();
    }

    public static Customer updateCustomerFromRequest(Customer customer, CustomerRequest customerRequest) {
        customer.setName(customerRequest.getName());
        customer.setAge(customerRequest.getAge());
        customer.setAddress(customerRequest.getAddress());
        customer.setMobileNo(customerRequest.getMobileNo());
        customer.setEmail(customerRequest.getEmail());
        return customer;
    }
}
