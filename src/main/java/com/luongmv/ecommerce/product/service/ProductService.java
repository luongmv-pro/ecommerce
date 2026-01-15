package com.luongmv.ecommerce.product.service;

import com.luongmv.ecommerce.product.dto.ProductCreateRequest;
import com.luongmv.ecommerce.product.dto.ProductResponse;
import com.luongmv.ecommerce.product.entity.Product;
import com.luongmv.ecommerce.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse create(ProductCreateRequest request) {

        Product product = new Product(
                request.getName(),
                request.getPrice(),
                request.getStock(),
                request.getDescription()
        );

        Product saved = productRepository.save(product);

        return new ProductResponse(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getStock()
        );
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getStock()
                ))
                .toList();
    }
}
