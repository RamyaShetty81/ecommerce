package com.example.ecommerce.service.implementation;

import com.example.ecommerce.dto.requestDto.CustomerRequest;
import com.example.ecommerce.dto.responseDto.CustomerResponse;
import com.example.ecommerce.exception.EmailIdNotPresentException;
import com.example.ecommerce.exception.IdNotPresentException;
import com.example.ecommerce.exception.MobileNoAlreadyPresentException;
import com.example.ecommerce.exception.NoCustomerException;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public CustomerResponse getCustomerByEmail(String email) throws EmailIdNotPresentException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new EmailIdNotPresentException("Email not found");
        }

        return CustomerTransformer.customerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerById(Integer id) throws IdNotPresentException {
        return customerRepository.findById(id)
                .map(CustomerTransformer::customerToCustomerResponse)
                .orElseThrow(() -> new IdNotPresentException("Customer not found"));
    }

    @Override
    public List<CustomerResponse> getAllCustomers() throws NoCustomerException {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new NoCustomerException("No customers found");
        }

        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer : customers) {
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponses;
    }

    @Override
    public String updateCustomerByEmail(CustomerRequest customerRequest) throws EmailIdNotPresentException {
        Customer customer = customerRepository.findByEmail(customerRequest.getEmail());
        if (customer == null) {
            throw new EmailIdNotPresentException("Email not found");
        }

        Customer updatedCustomer = CustomerTransformer.updateCustomerFromRequest(customer, customerRequest);
        customerRepository.save(updatedCustomer);

        return "Customer updated successfully";
    }

    @Override
    public String deleteCustomerByEmail(String email) throws EmailIdNotPresentException {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new EmailIdNotPresentException("Email not found");
        }

        customerRepository.delete(customer);
        return "Customer deleted successfully";
    }

    @Override
    public String deleteCustomerById(Integer id) throws IdNotPresentException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IdNotPresentException("Customer not found"));

        customerRepository.delete(customer);
        return "Customer deleted successfully";
    }

    @Override
    public List<CustomerResponse> getCustomersByAge(Integer age) throws NoCustomerException {
        List<Customer> customers = customerRepository.findByAgeGreaterThanEqual(age);
        if (customers.isEmpty()) {
            throw new NoCustomerException("No customers found");
        }

        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer : customers) {
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponses;
    }
}


