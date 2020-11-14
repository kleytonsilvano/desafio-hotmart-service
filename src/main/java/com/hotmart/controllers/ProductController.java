package com.hotmart.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.models.Product;
import com.hotmart.repository.ProductRepository;
import com.hotmart.utils.ClassConverter;

import gen.api.ProductsApi;
import gen.models.ProductModel;

@RestController
public class ProductController implements ProductsApi {
	
	@Autowired
	private ProductRepository repository;

	@Override
	public ResponseEntity<Void> addProduct(@Valid @RequestBody ProductModel body) {
		
    	if( body != null ) {

    		Product product = (Product) ClassConverter.copyProperties(new Product(), body);
    		product.setCreationDate(new Date());
    		repository.save(product);
    		return new ResponseEntity<>(HttpStatus.CREATED);
    		
    	}

    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

	@SuppressWarnings("unchecked")
	@Override
    public ResponseEntity<List<ProductModel>> listProducts() {
    	
    	List<Product> products = repository.findAll();
    	
    	if( products != null && !products.isEmpty()) {
    		
    		List<ProductModel> r = (List<ProductModel>) ClassConverter.copyProperties(new ArrayList<ProductModel>(), products);
    		return new ResponseEntity<>(r, HttpStatus.OK);
    		
    	}
    	
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    }

	@Override
	public ResponseEntity<ProductModel> findProductById(@PathVariable("idProduct") Long idProduct) {
		
		Optional<Product> products = repository.findById(idProduct);
    	
    	if(products.isPresent()) {
    		
    		ProductModel r = (ProductModel) ClassConverter.copyProperties(new ProductModel(), products);
    		return new ResponseEntity<>(r, HttpStatus.OK);
    		
    	}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}

