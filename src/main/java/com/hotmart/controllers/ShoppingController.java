package com.hotmart.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.models.db.Buyer;
import com.hotmart.repository.BuyerRepository;
import com.hotmart.repository.SalesmanRepository;

import gen.api.ShoppingsApi;
import gen.models.ShoppingProduct;

@RestController
public class ShoppingController implements ShoppingsApi {

	@Autowired
	private BuyerRepository buyerRepository;

	@Autowired
	private SalesmanRepository repository;
	
	
	public ResponseEntity<Void> buyProduct(@Valid @RequestBody ShoppingProduct body) {
		
		Optional<Buyer> buyer = buyerRepository.findById(body.getIdCustomer());
		
		if(buyer.isPresent()) {
			
			
			
		}

    	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

}
