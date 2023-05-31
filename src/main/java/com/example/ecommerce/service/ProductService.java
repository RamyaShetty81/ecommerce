package com.example.ecommerce.service;

import com.example.ecommerce.Enum.Category;
import com.example.ecommerce.dto.requestDto.ProductRequest;
import com.example.ecommerce.dto.responseDto.ProductResponse;
import com.example.ecommerce.exception.EmailIdNotPresentException;
import com.example.ecommerce.exception.IdNotPresentException;
import com.example.ecommerce.exception.NoSuchProductException;
import com.example.ecommerce.model.Item;
import com.example.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public ProductResponse addProduct(ProductRequest productRequest) throws IdNotPresentException;

    public List<ProductResponse> getParticularCategoryProducts(Category category) throws NoSuchProductException;

    public List<ProductResponse> getProductsBySellerEmailId(String emailId) throws EmailIdNotPresentException;

    public String deleteProductBySellerIdAndProductId(String sellerId, String productId) throws IdNotPresentException, NoSuchProductException;

    public List<ProductResponse> getTop5CostliestProducts() throws NoSuchProductException;

    public List<ProductResponse> getOutOfStockProducts() throws NoSuchProductException;

    public List<ProductResponse> getProductsWhoseQuantityLessThan10() throws NoSuchProductException;

    public ProductResponse cheapestProductInCategory(Category category) throws NoSuchProductException;

    public ProductResponse costliestProductInCategory(Category category) throws NoSuchProductException;

    public List<ProductResponse> getAllProductsByPriceAndCategory(Integer price, Category category) throws NoSuchProductException;


    void decreaseProductQuantity(Item item) throws Exception;
}
