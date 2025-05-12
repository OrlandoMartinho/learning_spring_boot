package com.example.crud.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.example.crud.domain.products.Product;
import com.example.crud.domain.products.ProductRepository;
import com.example.crud.domain.products.RequestProduct;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
         
            Product products = new Product(data);
            System.out.println("Creating product: " + data);
            
            productRepository.save(products);
           
            return ResponseEntity.ok().build();
            
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody @Valid RequestProduct data) {
       Optional<Product> product = productRepository.findById(id);
    
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var productToUpdate = product.get();
        productToUpdate.setName(data.name());
        productToUpdate.setPrice_in_cents(data.price_in_cents());
        productRepository.save(productToUpdate);
        return ResponseEntity.ok(productToUpdate);
      
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product.get());
        return ResponseEntity.noContent().build();
    }
}