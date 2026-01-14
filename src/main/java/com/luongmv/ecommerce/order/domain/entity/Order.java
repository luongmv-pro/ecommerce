package com.luongmv.ecommerce.order.domain.entity;

import com.luongmv.ecommerce.common.entity.BaseEntity;
import com.luongmv.ecommerce.order.domain.OrderStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    protected Order() {}

    public Order(Long userId) {
        this.userId = userId;
        this.status = OrderStatus.NEW;
    }
}
