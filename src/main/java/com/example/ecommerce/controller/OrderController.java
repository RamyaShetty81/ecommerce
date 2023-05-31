package com.example.ecommerce.controller;

import com.example.ecommerce.dto.requestDto.OrderRequest;
import com.example.ecommerce.dto.responseDto.OrderResponse;
import com.example.ecommerce.dto.responseDto.SellerResponse;
import com.example.ecommerce.model.Card;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.model.Ordered;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @PostMapping("/order-cart")
    public ResponseEntity placeOrder(@RequestBody Customer customer, @RequestBody Card card) {

        Ordered order;
        try {
            order =  orderService.placeOrder(customer,card);
            return new ResponseEntity(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // API to order and item individually
    @PostMapping("/place")
    public ResponseEntity placeDirectOrder(@RequestBody OrderRequest orderRequest) {

        OrderResponse orderResponse;
        try {
            orderResponse =  orderService.placeDirectOrder(orderRequest);
            return new ResponseEntity(orderResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get all the orders for a customer

    // get recent 5 orders

    // delete an order from the order list

    // select the order and also tell the customer name with the highest total value.


     @GetMapping("/{customerId}")
     public ResponseEntity getOrdersForCustomer(@PathVariable String customerId) {

         List<OrderResponse> list;
         try {
             list = orderService.getOrdersForCustomer(customerId);
             return new ResponseEntity(list, HttpStatus.CREATED);
         }
         catch (Exception e)
         {
             return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
         }
    }

    @GetMapping("/recent")
    public ResponseEntity getRecentOrders() {

        List<OrderResponse> list;
        try {
            list = orderService.getRecentOrders();
            return new ResponseEntity(list, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Integer orderId) {
        try {
            String response = orderService.deleteOrder(orderId);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/highestTotal")
    public ResponseEntity getOrderWithHighestTotal() {
        OrderResponse response ;

        try {
            response =  orderService.getOrderWithHighestTotal();
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}