package com.example.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.domain.products.ProductsRepository;
@RestController
@RequestMapping("/products")

public class ProductsController {


    @Autowired
    private ProductsRepository productsRepository;
    @GetMapping

    public ResponseEntity getAllProduct(){

        var products = productsRepository.findAll();
        return ResponseEntity.ok(products);
    }

}
