package com.luongmv.ecommerce.product.entity;

import com.luongmv.ecommerce.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 500)
    private String description;

    protected Product() {}

    public Product(String name, BigDecimal price, Integer stock, String description) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }

    public void decreaseStock(int quantity) {
        if (this.stock < quantity) {
            throw new RuntimeException("Not enough stock");
        }
        this.stock -= quantity;
    }
}
