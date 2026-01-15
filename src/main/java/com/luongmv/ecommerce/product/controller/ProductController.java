package com.luongmv.ecommerce.product.controller;

import com.luongmv.ecommerce.product.dto.ProductCreateRequest;
import com.luongmv.ecommerce.product.dto.ProductResponse;
import com.luongmv.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
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
    public List<ProductResponse> list() {
        return productService.findAll();
    }

    // ADMIN
    @PostMapping
    public ProductResponse create(
            @Valid @RequestBody ProductCreateRequest request
    ) {
        return productService.create(request);
    }
}
