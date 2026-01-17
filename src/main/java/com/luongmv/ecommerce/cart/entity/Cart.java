package com.luongmv.ecommerce.cart.entity;

import com.luongmv.ecommerce.common.entity.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CartItem> items = new ArrayList<>();

    protected Cart() {}

    public Cart(Long userId) {
        this.userId = userId;
    }

    public Long getUserId(){
        return userId;
    }

    public List<CartItem> getItems() {
        return items;
    }


    /* DOMAIN LOGIC */

    public void addItem(Long productId, int quantity) {

        CartItem existing = items.stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.increase(quantity);
        } else  {
            items.add(new CartItem(this, productId, quantity));
        }
    }

    public void updateItem(Long productId, int quantity) {
        CartItem item = items.stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (quantity <= 0) {
            items.remove(item);
        } else {
            item.update(quantity);
        }
    }

    public void removeItem(Long productId) {
        items.removeIf(i -> i.getProductId().equals(productId));
    }

}
