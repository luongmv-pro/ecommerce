package com.luongmv.ecommerce.product.controller;

import com.luongmv.ecommerce.product.dto.ProductCreateRequest;
import com.luongmv.ecommerce.product.dto.ProductDetailResponse;
import com.luongmv.ecommerce.product.dto.ProductResponse;
import com.luongmv.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // PUBLIC
    @GetMapping
    public Page<ProductResponse> list(
            @PageableDefault(size=2, sort = "id") Pageable pageable
            ) {
        return productService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ProductDetailResponse detail(@PathVariable Long id) {
        return productService.findById(id);
    }

    // ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProductResponse create(
            @Valid @RequestBody ProductCreateRequest request
    ) {
        return productService.create(request);
    }

}
