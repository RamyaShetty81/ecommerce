package com.example.ecommerce.transformer;

import com.example.ecommerce.Enum.Status;
import com.example.ecommerce.dto.requestDto.ProductRequest;
import com.example.ecommerce.dto.responseDto.ProductResponse;
import com.example.ecommerce.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTransformer {
    public static Product productRequestToProduct(ProductRequest productRequest)
    {
        return Product.builder()
                .name(productRequest.getProductName())
                .category(productRequest.getCategory())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .status(Status.AVAILABLE)
                .build();
    }

    public static ProductResponse productToProductResponse(Product product)
    {
        return ProductResponse.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .quantity(product.getQuantity())
                .status(product.getStatus())
                .build();
    }
}
