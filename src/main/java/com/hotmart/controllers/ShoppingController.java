package com.hotmart.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gen.api.ShoppingsApi;
import gen.models.ShoppingProduct;

@RestController
public class ShoppingController implements ShoppingsApi {
	
	public ResponseEntity<Void> buyProduct(@Valid @RequestBody ShoppingProduct body) {
		
		return null;

    }

}
