package com.example.ecommerce.controller;

import com.example.ecommerce.dto.requestDto.CartRequest;
import com.example.ecommerce.dto.requestDto.ItemRequest;
import com.example.ecommerce.dto.responseDto.CartResponse;
import com.example.ecommerce.dto.responseDto.ItemResponse;
import com.example.ecommerce.dto.responseDto.OrderResponse;
import com.example.ecommerce.model.Item;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ItemService;
import com.example.ecommerce.service.implementation.ItemServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemServiceImplementation itemServiceImplementation;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequest itemRequest) throws Exception {

        try{
          Item savedItem = itemService.addItem(itemRequest);
          CartResponse cartResponse = cartService.saveToCart(itemRequest.getCustomerId(),savedItem);
          return new ResponseEntity(cartResponse, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity checkOutCart(@RequestBody CartRequest CartRequest) throws Exception {


        try{
            OrderResponse orderResponse;
            orderResponse =   cartService.checkOutCart(CartRequest);
            return new ResponseEntity(orderResponse, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // remove from cart

    // view all items in cart

    // email sending
    

     @DeleteMapping("/remove/{cartId}/{itemId}")
    public ResponseEntity removeFromCart(@PathVariable Integer cartId,@PathVariable Integer itemId) {
        try {
            String response = cartService.removeItemFromCart(cartId,itemId);
            return new ResponseEntity(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/view")
    public ResponseEntity<List<ItemResponse>> viewCartItems(@RequestParam Integer customerId) {
        try {
            List<ItemResponse> items = cartService.getAllItemsInCart(customerId);
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/sendEmail")
//    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDto emailRequestDto) {
//        try {
//            cartService.sendEmail(emailRequestDto);
//            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

}
