package com.luongmv.ecommerce.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("deletedFilter");
    }
}
