package com.news.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.news.exceptions.ValidationException;
import com.news.models.db.Buyer;
import com.news.models.db.Product;
import com.news.models.db.Sale;
import com.news.models.db.Salesman;
import com.news.models.enums.ValidationMessage;
import com.news.repository.BuyerRepository;
import com.news.repository.ProductRepository;
import com.news.repository.SaleRepository;
import com.news.repository.SalesmanRepository;

import gen.api.ShoppingsApi;
import gen.models.ShoppingProduct;

@RestController
public class ShoppingController implements ShoppingsApi {

	@Autowired
	private BuyerRepository buyerRepository;

	@Autowired
	private SalesmanRepository salesmanRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SaleRepository saleRepository;

	public ResponseEntity<Void> buyProduct(@Valid @RequestBody ShoppingProduct body) {
		
		Optional<Buyer> buyer = buyerRepository.findById(body.getIdCustomer());
		
		if(buyer.isPresent()) {
			
			Optional<Salesman> salesman = findSalesman(body.getIdSalesman());
			Optional<Product> product = findProduct(body.getIdProduct());
			
			saveSale(buyer, salesman, product, body.getRating());

    		return new ResponseEntity<>(HttpStatus.CREATED);
			
		}

    	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

	private void saveSale(Optional<Buyer> buyer, Optional<Salesman> salesman, Optional<Product> product, Integer rating) {
		
		Sale sale = new Sale();
		sale.setBuyer(buyer.get());
		sale.setProduct(product.get());
		sale.setSalesman(salesman.get());
		sale.setRate(rating);
		saleRepository.save(sale);
		
	}

	private Optional<Salesman> findSalesman(Long idSalesman) {
		
		Optional<Salesman> salesman = salesmanRepository.findById(idSalesman);
		
		if(!salesman.isPresent()) {
			
			throw new ValidationException(ValidationMessage.SALESMAN_NOT_FOUND);
			
		}
		
		return salesman;
	}
	
	private Optional<Product> findProduct(Long idProduct) {
		
		Optional<Product> product = productRepository.findById(idProduct);
		
		if(!product.isPresent()) {
			
			throw new ValidationException(ValidationMessage.PRODUCT_NOT_FOUND);
			
		}
		
		return product;
	}

}
