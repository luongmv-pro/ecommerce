package com.luongmv.ecommerce.user.entity;

import com.luongmv.ecommerce.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "index_users_email", columnList = "email")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_email", columnNames = "email")
        }
)
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    protected User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
