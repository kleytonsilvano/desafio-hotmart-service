package com.news.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.news.models.db.Salesman;
import com.news.repository.SalesmanRepository;

import gen.api.SalesmansApi;
import gen.models.SalesmanModel;

@RestController
public class SalesmanController implements SalesmansApi {
	
	@Autowired
	private SalesmanRepository repository;
	
	@Override
	public ResponseEntity<Void> addSalesman(@Valid @RequestBody SalesmanModel body) {

    	if( body != null ) {

    		saveSalesman(body);
    		
    		return new ResponseEntity<>(HttpStatus.CREATED);
    		
    	}

    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

	private void saveSalesman(SalesmanModel body) {

		Salesman salesman = new Salesman();
		salesman.setName(body.getName());
		repository.save(salesman);
	}

}