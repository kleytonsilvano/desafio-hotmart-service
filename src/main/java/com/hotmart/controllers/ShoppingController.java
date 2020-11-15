package com.hotmart.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.exceptions.ValidationException;
import com.hotmart.models.db.Buyer;
import com.hotmart.models.db.Product;
import com.hotmart.models.db.Rating;
import com.hotmart.models.db.Sale;
import com.hotmart.models.db.Salesman;
import com.hotmart.models.enums.ValidationMessage;
import com.hotmart.repository.BuyerRepository;
import com.hotmart.repository.ProductRepository;
import com.hotmart.repository.RatingRepository;
import com.hotmart.repository.SaleRepository;
import com.hotmart.repository.SalesmanRepository;

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

	@Autowired
	private RatingRepository ratingRepository;
	
	public ResponseEntity<Void> buyProduct(@Valid @RequestBody ShoppingProduct body) {
		
		Optional<Buyer> buyer = buyerRepository.findById(body.getIdCustomer());
		
		if(buyer.isPresent()) {
			
			Optional<Salesman> salesman = findSalesman(body.getIdSalesman());
			Optional<Product> product = findProduct(body.getIdProduct());
			
			Sale sale = saveSale(buyer, salesman, product);
			saveRating(sale, body.getRating());

    		return new ResponseEntity<>(HttpStatus.CREATED);
			
		}

    	return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

	private void saveRating(Sale sale, Integer rate) {
		
		Rating rating = new Rating();
		rating.setRate(rate);
		rating.setSale(sale);
		ratingRepository.save(rating);
		
	}

	private Sale saveSale(Optional<Buyer> buyer, Optional<Salesman> salesman, Optional<Product> product) {
		
		Sale sale = new Sale();
		sale.setBuyer(buyer.get());
		sale.setProduct(product.get());
		sale.setSalesman(salesman.get());
		saleRepository.save(sale);
		
		return sale;
		
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
