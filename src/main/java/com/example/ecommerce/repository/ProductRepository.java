package com.example.ecommerce.repository;

import com.example.ecommerce.Enum.Category;
import com.example.ecommerce.Enum.Status;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategory(Category category);

    List<Product> findBySeller(Seller seller);

    List<Product> findByStatus(Status status);

    List<Product> findByQuantityLessThanEqual(Integer quantity);

//    @Query(value = "select p from Product p where p.productId=:productId and p.sellerId=:sellerId")
//    List<Product> findAllProductsBySellerIdAndProductId(Integer sellerId, Integer productId);

    void deleteBySellerIdAndId(Integer sellerId, Integer productId);

    @Query(value = "select p from Product p where p.price>:price and p.category=:category")
    List<Product> findAllProductsByPriceAndCategory(Integer price, Category category);

    @Query(value = "select * from product p where p.category =:category order by p.price desc limit 1", nativeQuery = true)
    Product findByCategoryCheap(Category category);

    @Query(value = "select * from product p where p.category =:category order by p.price  limit 1", nativeQuery = true)
    Product findByCategoryCostly(Category category);

    @Query(value = "select * from product p order by p.price  limit 5", nativeQuery = true)
    List<Product> findByPrice();

}
