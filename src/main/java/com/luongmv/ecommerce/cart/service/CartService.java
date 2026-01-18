package com.luongmv.ecommerce.cart.service;

import com.luongmv.ecommerce.cart.dto.CartItemResponse;
import com.luongmv.ecommerce.cart.dto.CartResponse;
import com.luongmv.ecommerce.cart.entity.Cart;
import com.luongmv.ecommerce.cart.repository.CartRepository;
import com.luongmv.ecommerce.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(
            CartRepository cartRepository,
            ProductRepository productRepository
    ) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Cart getOrCreate(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(new Cart(userId)));
    }

    @Transactional
    public void addItem(Long userId, Long productId, int quantity) {
        //validate product
        productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = getOrCreate(userId);
        cart.addItem(productId, quantity);
    }

    @Transactional
    public void updateItem(Long userId, Long productId, int quantity) {
        Cart cart = getOrCreate(userId);
        cart.updateItem(productId, quantity);
    }

    @Transactional
    public void removeItem(Long userId, Long productId) {
        Cart cart = getOrCreate(userId);
        cart.removeItem(productId);
    }

    @Transactional
    public CartResponse viewCart(Long userId) {

        Cart cart = getOrCreate(userId);

        return new CartResponse(
                cart.getUserId(),
                cart.getItems().stream()
                        .map(i -> new CartItemResponse(
                                i.getProductId(),
                                i.getQuantity()
                        ))
                        .toList()
        );
    }
}
