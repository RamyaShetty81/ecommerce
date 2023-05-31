package com.example.ecommerce.repository;

import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered,Integer> {

    List<Ordered> findByCustomer(Customer customer);

    List<Ordered> findTop5ByOrderByOrderDateDesc();

    Ordered findFirstByOrderByTotalValueDesc();
}
