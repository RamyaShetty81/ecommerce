package com.example.ecommerce.transformer;

import com.example.ecommerce.dto.requestDto.ItemRequest;
import com.example.ecommerce.dto.responseDto.ItemResponse;
import com.example.ecommerce.model.Item;

public class ItemTransformer {
    public static Item itemRequestToItem(ItemRequest itemRequest)
    {
        return Item.builder()
                .requiredQuantity(itemRequest.getRequiredQuantity())
                .build();
    }

    public static ItemResponse itemToItemResponse(Item item)
    {
        return ItemResponse.builder()
                .priceOfItem(item.getProduct().getPrice())
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .totalPrice(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }
}
