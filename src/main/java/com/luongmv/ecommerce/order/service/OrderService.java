package com.luongmv.ecommerce.order.service;

import com.luongmv.ecommerce.cart.entity.Cart;
import com.luongmv.ecommerce.cart.repository.CartRepository;
import com.luongmv.ecommerce.order.dto.OrderResponse;
import com.luongmv.ecommerce.order.entity.Order;
import com.luongmv.ecommerce.order.repository.OrderRepository;
import com.luongmv.ecommerce.product.entity.Product;
import com.luongmv.ecommerce.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(
            CartRepository cartRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository
    ) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderResponse placeOrder(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order(userId);

        cart.getItems().forEach(item -> {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock");
            }

            long price = product.getPrice().longValue();

            //add order item
            order.addItem(
                    product.getId(),
                    item.getQuantity(),
                    price
            );

            //update stock
            product.decreaseStock(item.getQuantity());
        });

        orderRepository.save(order);

        //clear cart
        cart.getItems().clear();

        return new OrderResponse(order.getId(), order.getStatus().name());
    }
}
