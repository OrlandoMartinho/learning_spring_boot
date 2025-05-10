package com.example.crud.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/products")

public class ProductsController {

    @GetMapping

    public ResponseEntity getAllProduct(){
        return ResponseEntity.ok("deu Ok");
    }

}
