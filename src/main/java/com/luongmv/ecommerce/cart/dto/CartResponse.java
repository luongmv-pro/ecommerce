package com.luongmv.ecommerce.cart.dto;

import java.util.List;

public class CartResponse {

    private final Long userId;
    private final List<CartItemResponse> items;

    public CartResponse(Long userId, List<CartItemResponse> items) {
        this.userId = userId;
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public List<CartItemResponse> getItems() {
        return items;
    }
}
