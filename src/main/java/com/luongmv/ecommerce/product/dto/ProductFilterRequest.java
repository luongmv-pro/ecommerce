package com.luongmv.ecommerce.product.dto;

import java.math.BigDecimal;

public class ProductFilterRequest {

    private String keyword;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getKeyword() {
        return keyword;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }
}
