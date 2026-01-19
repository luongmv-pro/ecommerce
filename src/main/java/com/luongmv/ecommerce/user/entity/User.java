package com.luongmv.ecommerce.user.entity;

import com.luongmv.ecommerce.common.entity.BaseEntity;
import com.luongmv.ecommerce.user.domain.UserRole;
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

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole role;

    protected User() {}

    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}
