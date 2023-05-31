package com.example.ecommerce.service;

import com.example.ecommerce.dto.requestDto.OrderRequest;
import com.example.ecommerce.dto.responseDto.OrderResponse;
import com.example.ecommerce.exception.CustomerNotExistException;
import com.example.ecommerce.exception.IdNotPresentException;
import com.example.ecommerce.model.Card;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.Ordered;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {


    public Ordered placeOrder(Customer customer, Card card) throws Exception;

    public OrderResponse placeDirectOrder(OrderRequest orderRequest) throws Exception;

    List<OrderResponse> getOrdersForCustomer(Integer customerId) throws CustomerNotExistException;

    List<OrderResponse> getRecentOrders();

    String deleteOrder(Integer orderId) throws IdNotPresentException;

    OrderResponse getOrderWithHighestTotal();

}
