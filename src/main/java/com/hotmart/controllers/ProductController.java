package com.hotmart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.models.Product;
import com.hotmart.repository.ProductRepository;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;

//    @PostMapping
//    public void createProduct() {
//    	
//    }
//    
    @GetMapping
    public List<Product> listProducts() {
    	return repository.findAll();
    }
//
//    @GetMapping
//    public void findProduct(Integer codeProduct) {
//    	
//    }
    
}

