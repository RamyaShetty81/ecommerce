package com.example.ecommerce.service.implementation;

import com.example.ecommerce.dto.requestDto.CustomerRequest;
import com.example.ecommerce.dto.responseDto.CustomerResponse;
import com.example.ecommerce.exception.MobileNoAlreadyPresentException;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  CustomerServiceImplementation implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) throws MobileNoAlreadyPresentException {
        if(customerRepository.findByMobileNo(customerRequest.getMobileNo())!=null)
            throw new MobileNoAlreadyPresentException("Customer already registered!!!");
        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);

        Cart cart = Cart.builder()
                .cartTotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();
        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.customerToCustomerResponse(customer);


            }
}


