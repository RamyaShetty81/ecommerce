package com.example.ecommerce.controller;

import com.example.ecommerce.Enum.Category;
import com.example.ecommerce.dto.requestDto.ProductRequest;
import com.example.ecommerce.dto.responseDto.ProductResponse;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest)
    {
        try {
            ProductResponse productResponse = productService.addProduct(productRequest);
            return new ResponseEntity(productResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-by-category")
    public ResponseEntity getParticularCategoryProducts(@RequestParam Category category)
    {
        try {
            List<ProductResponse> productResponses = productService.getParticularCategoryProducts(category);
            return new ResponseEntity(productResponses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-by-emailId")
    public ResponseEntity getProductsBySellerEmailId(@RequestParam   String emailId)
    {
        try {
            List<ProductResponse> productResponses = productService.getProductsBySellerEmailId(emailId);
            return new ResponseEntity(productResponses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-By-SellerId-ProductId")
    public ResponseEntity deleteProductBySellerIdAndProductId(@RequestParam String sellerId,@RequestParam String productId)
    {
        try {
           String response = productService.deleteProductBySellerIdAndProductId(sellerId, productId);
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/top-5-products")
    public ResponseEntity getTop5CostliestProducts()
    {
        try {
            List<ProductResponse> productResponses = productService.getTop5CostliestProducts();
            return new ResponseEntity(productResponses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/out-of-stock-products")
    public ResponseEntity getOutOfStockProducts()
    {
        try {
            List<ProductResponse> productResponses = productService.getOutOfStockProducts();
            return new ResponseEntity(productResponses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/quantity-lessthan10")
    public ResponseEntity getProductsWhoseQuantityLessThan10()
    {
        try {
            List<ProductResponse> productResponses = productService.getProductsWhoseQuantityLessThan10();
            return new ResponseEntity(productResponses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cheapProduct-by-category")
    public ResponseEntity cheapestProductInCategory(@RequestParam Category category)
    {
        try {
            ProductResponse productResponse = productService.cheapestProductInCategory(category);
            return new ResponseEntity(productResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/costliestProduct-by-category")
    public ResponseEntity costliestProductInCategory(@RequestParam Category category)
    {
        try {
            ProductResponse productResponse = productService.costliestProductInCategory(category);
            return new ResponseEntity(productResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-ByPriceAndCategory")
    public ResponseEntity getAllProductsByPriceAndCategory(@PathVariable Integer price,@PathVariable Category category)
    {
        try {
            List<ProductResponse> productResponses = productService.getAllProductsByPriceAndCategory(price,category);
            return new ResponseEntity(productResponses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
