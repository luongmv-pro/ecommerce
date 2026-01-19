package com.luongmv.ecommerce.cart.dto;

public class CartItemResponse {

    private final Long productId;
    private final int quantity;

    public CartItemResponse(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
