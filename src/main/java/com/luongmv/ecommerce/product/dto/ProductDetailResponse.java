package com.luongmv.ecommerce.product.dto;

import java.math.BigDecimal;

public class ProductDetailResponse {

    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final Integer stock;
    private final String description;

    public ProductDetailResponse(Long id, String name, BigDecimal price, Integer stock, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public Long getId() {
        return id;
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
}
