package com.luongmv.ecommerce.cart.controller;

import com.luongmv.ecommerce.cart.dto.CartResponse;
import com.luongmv.ecommerce.cart.entity.Cart;
import com.luongmv.ecommerce.cart.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public CartResponse view(Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        return cartService.viewCart(userId);
    }

    @PostMapping("/items")
    public void addItem(
            Authentication auth,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        cartService.addItem(
                (Long) auth.getPrincipal(),
                productId,
                quantity
        );
    }

    @PutMapping("/items/{productId}")
    public void updateItem(
            Authentication auth,
            @PathVariable Long productId,
            @RequestParam int quantity
    ) {
        cartService.updateItem(
                (Long) auth.getPrincipal(),
                productId,
                quantity
        );
    }

    @DeleteMapping("/items/{productId}")
    public void removeItem(
            Authentication auth,
            @PathVariable Long productId
    ) {
        cartService.removeItem(
                (Long) auth.getPrincipal(),
                productId
        );
    }
}
