package com.luongmv.ecommerce.order.entity;

import com.luongmv.ecommerce.common.entity.BaseEntity;
import com.luongmv.ecommerce.order.OrderStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "orders")
public class Order extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<>();

    protected Order() {}

    public Order(Long userId) {
        this.userId = userId;
        this.status = OrderStatus.NEW;
    }

    public Long getUserId() {
        return userId;
    }

    public OrderStatus getStatus(){
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    /* domain logic */

    public void addItem(Long productId, int quantity, long price) {
        items.add(new OrderItem(this, productId, quantity, price));
    }
}
