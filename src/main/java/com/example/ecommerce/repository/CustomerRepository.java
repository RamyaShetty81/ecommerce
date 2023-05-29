package com.example.ecommerce.repository;

import com.example.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByMobileNo(String mobileNo);

    Customer findByEmail(String email);

    List<Customer> findByAgeGreaterThanEqual(Integer age);
}
