package com.luongmv.ecommerce.order.controller;

import com.luongmv.ecommerce.order.dto.OrderResponse;
import com.luongmv.ecommerce.order.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse placeOrder(Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        return orderService.placeOrder(userId);
    }
}
