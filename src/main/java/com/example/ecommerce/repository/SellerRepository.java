package com.example.ecommerce.repository;

import com.example.ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller findByEmailId(String emailId);
    Optional<Seller> findById(Integer id);
}
