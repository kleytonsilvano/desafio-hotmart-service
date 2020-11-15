package com.hotmart.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.models.db.Buyer;
import com.hotmart.repository.BuyerRepository;

import gen.api.CustomersApi;
import gen.models.CustomerModel;

@RestController
public class CustomerController implements CustomersApi {
	
	@Autowired
	private BuyerRepository repository;
	
	@Override
	public ResponseEntity<Void> addCustomer(@Valid @RequestBody CustomerModel body) {

    	if( body != null ) {

    		saveCustomer(body);
    		
    		return new ResponseEntity<>(HttpStatus.CREATED);
    		
    	}

    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

	private void saveCustomer(CustomerModel body) {

		Buyer buyer = new Buyer();
		buyer.setName(body.getName());
		repository.save(buyer);
	}

}
