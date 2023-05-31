package com.example.ecommerce.service;

import com.example.ecommerce.dto.requestDto.ItemRequest;
import com.example.ecommerce.exception.CustomerNotExistException;
import com.example.ecommerce.exception.NoSuchProductException;
import com.example.ecommerce.exception.ProductOutOfStock;
import com.example.ecommerce.model.Item;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    public Item addItem(ItemRequest itemRequest) throws CustomerNotExistException, NoSuchProductException, ProductOutOfStock;
}
