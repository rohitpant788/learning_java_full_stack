package com.rohit.docker.product_service.controller;

import com.rohit.docker.product_service.entity.Product;
import com.rohit.docker.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot";
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public String createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return "Product has been created";
    }
}
