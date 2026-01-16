package com.luongmv.ecommerce.product.service;

import com.luongmv.ecommerce.product.dto.ProductCreateRequest;
import com.luongmv.ecommerce.product.dto.ProductDetailResponse;
import com.luongmv.ecommerce.product.dto.ProductFilterRequest;
import com.luongmv.ecommerce.product.dto.ProductResponse;
import com.luongmv.ecommerce.product.entity.Product;
import com.luongmv.ecommerce.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getStock()
                ));

    }

    public ProductDetailResponse findById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductDetailResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getDescription()
        );
    }

    public Page<ProductResponse> search(
            ProductFilterRequest filter,
            Pageable pageable
    ) {
        return productRepository.search(
                filter.getKeyword(),
                filter.getMinPrice(),
                filter.getMaxPrice(),
                pageable
        ).map(p -> new ProductResponse(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getStock()
        ));
    }
}
