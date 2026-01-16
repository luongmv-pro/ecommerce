package com.luongmv.ecommerce.order.entity;

import com.luongmv.ecommerce.common.entity.BaseEntity;
import com.luongmv.ecommerce.order.OrderStatus;
import jakarta.persistence.*;

@Entity
@Table(
        name = "orders",
        indexes = {
                @Index(name = "idx_orders_user_id", columnList = "user_id"),
                @Index(name = "idx_orders_created_at", columnList = "created_at")
        }
)
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
