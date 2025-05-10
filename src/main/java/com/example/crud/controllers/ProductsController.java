package com.example.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.example.crud.domain.products.Product;
import com.example.crud.domain.products.ProductRepository;
import com.example.crud.domain.products.RequestProduct;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid RequestProduct data) {
        try {
            // Validate input data
            if (data.name() == null || data.name().isEmpty()) {
                return ResponseEntity.badRequest().body("Product name cannot be null or empty");
            }
            if (data.price_in_cents() == null || data.price_in_cents() <= 0) {
                return ResponseEntity.badRequest().body("Product price must be greater than zero");
            }

            // Create and save product
            Product products = new Product(data);
            System.out.println("Creating product: " + data);
            
            productRepository.save(products);
            System.out.println("Product created successfully: " + products);
            
            return ResponseEntity.ok().build();
            
        } catch (Exception e) {
            System.err.println("Error creating product: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Error processing your request");
        }
    }
}