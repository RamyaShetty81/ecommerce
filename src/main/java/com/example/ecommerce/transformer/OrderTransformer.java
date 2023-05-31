package com.example.ecommerce.transformer;

import com.example.ecommerce.dto.requestDto.OrderRequest;
import com.example.ecommerce.dto.responseDto.OrderResponse;
import com.example.ecommerce.model.Ordered;

import java.util.UUID;

public class OrderTransformer {

    public static OrderResponse orderToOrderResponse(Ordered ordered)
    {
        return OrderResponse.builder()
                .orderNo(ordered.getOrderNo())
                .orderDate(ordered.getOrderDate())
                .cardUsed(ordered.getCardUsed())
                .customerName(ordered.getCustomer().getName())
                .totalValue(ordered.getTotalValue())
                .build();
    }


}
