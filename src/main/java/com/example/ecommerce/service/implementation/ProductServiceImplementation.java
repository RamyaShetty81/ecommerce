package com.example.ecommerce.service.implementation;

import com.example.ecommerce.Enum.Category;
import com.example.ecommerce.Enum.Status;
import com.example.ecommerce.dto.requestDto.ProductRequest;
import com.example.ecommerce.dto.responseDto.ProductResponse;
import com.example.ecommerce.exception.EmailIdNotPresentException;
import com.example.ecommerce.exception.IdNotPresentException;
import com.example.ecommerce.exception.NoSuchProductException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Seller;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;


    @Override
    public ProductResponse addProduct(ProductRequest productRequest) throws IdNotPresentException {

        Seller seller = sellerRepository.findById(Integer.valueOf(productRequest.getSellerId())).get();
        if(seller.equals(null)) throw new IdNotPresentException("Seller doesn't exist");

        Product product = ProductTransformer.productRequestToProduct(productRequest);
        product.setSeller(seller);

        //add products to existing product
        seller.getProducts().add(product);
        sellerRepository.save(seller);//saves both parent and child

        return ProductTransformer.productToProductResponse(product);
    }

    @Override
    public List<ProductResponse> getParticularCategoryProducts(Category category) throws NoSuchProductException {
        List<Product> products = productRepository.findByCategory(category);
        if(products.size()==0)
            throw new NoSuchProductException("No products are available in category"+ category);

        List<ProductResponse> productResponses = productListToProductResponseList(products);
        return productResponses;

        }

    @Override
    public List<ProductResponse> getProductsBySellerEmailId(String emailId) throws EmailIdNotPresentException {
        try {
            Seller seller = sellerRepository.findByEmailId(emailId);
            List<Product> products = seller.getProducts();
            List<ProductResponse> productResponses = productListToProductResponseList(products);
            return productResponses;

        }
        catch (Exception e)
        {
            throw new EmailIdNotPresentException("Invalid EmailId");
        }
    }

    @Override
    public String deleteProductBySellerIdAndProductId(String sellerId, String productId) throws IdNotPresentException,
                                                                                             NoSuchProductException {

        Seller seller = sellerRepository.findById(Integer.valueOf(sellerId)).get();
        if(seller.equals(null)) throw new IdNotPresentException("Seller doesn't exist");

        try {
             productRepository.deleteBySellerIdAndId
                    (Integer.valueOf(sellerId),Integer.valueOf(productId));
            return "Products of seller Id: " + sellerId + " and Product Id: " + productId +" deleted successfully";

        }
        catch (Exception e)
        {
            throw new NoSuchProductException("Invalid product Id");
        }
    }

    @Override
    public List<ProductResponse> getTop5CostliestProducts() throws NoSuchProductException {
        try {
            List<Product> products = productRepository.findByPrice();
            List<ProductResponse> productResponses = productListToProductResponseList(products);
            return productResponses;
        }
        catch (Exception e)
        {
            throw new NoSuchProductException("We dont have any products currently!!!");
        }
    }

    @Override
    public List<ProductResponse> getOutOfStockProducts() throws NoSuchProductException {
        try {
            List<Product> products = productRepository.findByStatus(Status.OUT_OF_STOCK);
            List<ProductResponse> productResponses = productListToProductResponseList(products);
            return productResponses;
        }
        catch (Exception e)
        {
            throw new NoSuchProductException("All products are available to buy");
        }
    }

    @Override
    public List<ProductResponse> getProductsWhoseQuantityLessThan10() throws NoSuchProductException {
        try {
            List<Product> products = productRepository.findByQuantityLessThanEqual(10);
            List<ProductResponse> productResponses = productListToProductResponseList(products);
            return productResponses;
        }
        catch (Exception e)
        {
            throw new NoSuchProductException("All products have quantity more than 10!!!");
        }

    }

    @Override
    public ProductResponse cheapestProductInCategory(Category category) throws NoSuchProductException {

        try {
            Product product = productRepository.findByCategoryCheap(category);

            return ProductTransformer.productToProductResponse(product);
        }
        catch (Exception e)
        {
            throw new NoSuchProductException("No products available in this category");
        }
    }

    @Override
    public ProductResponse costliestProductInCategory(Category category) throws NoSuchProductException {
        try {
            Product product = productRepository.findByCategoryCostly(category);

            return ProductTransformer.productToProductResponse(product);
        }
        catch (Exception e)
        {
            throw new NoSuchProductException("No products available in this category");
        }
    }

    @Override
    public List<ProductResponse> getAllProductsByPriceAndCategory(Integer price, Category category) throws NoSuchProductException {
        try {
            List<Product> products = productRepository.findAllProductsByPriceAndCategory(price,category);
            List<ProductResponse> productResponses = productListToProductResponseList(products);
            return productResponses;
        }
        catch (Exception e)
        {
            throw new NoSuchProductException("Such products doesn't exists!!!");
        }
    }




    public List<ProductResponse> productListToProductResponseList(List<Product> products)
    {

            List<ProductResponse> productResponses = new ArrayList<>();
            for (Product product : products) {
                productResponses.add(ProductTransformer.productToProductResponse(product));
            }
            return productResponses;

    }
}
