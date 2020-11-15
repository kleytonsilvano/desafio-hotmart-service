package com.hotmart.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.converter.ProductModelConverter;
import com.hotmart.exceptions.BadRequestException;
import com.hotmart.models.Product;
import com.hotmart.models.ProductCategory;
import com.hotmart.repository.ProductCategoryRepository;
import com.hotmart.repository.ProductRepository;

import gen.api.ProductsApi;
import gen.models.ProductModel;

@RestController
public class ProductController implements ProductsApi {
	
	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@Autowired
	private ProductModelConverter converter;
	
	@Value("${DEFAULT_PAGE_SIZE}")
	private Integer defaultPageSize;

	@Override
	public ResponseEntity<Void> addProduct(@Valid @RequestBody ProductModel body) {
		
    	if( body != null ) {

    		saveProduct(body);
    		
    		return new ResponseEntity<>(HttpStatus.CREATED);
    		
    	}

    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

	@Override
    public ResponseEntity<List<ProductModel>> listProducts() {
		
    	List<Product> products = repository.findAll();
    	
    	if( products != null && !products.isEmpty()) {
    		
    		return new ResponseEntity<>(converter.converter(products), HttpStatus.OK);
    		
    	}
    	
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    }

	@Override
	public ResponseEntity<ProductModel> findProductById(@PathVariable("idProduct") Long idProduct) {
		
		Optional<Product> product = repository.findById(idProduct);
    	
    	if(product.isPresent()) {
    		
    		return new ResponseEntity<>(converter.converter(product.get()), HttpStatus.OK);
    		
    	}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	private List<ProductCategory> findCategories(ProductModel body) {
		
		List<ProductCategory> listReturn = new ArrayList<>();
		
		for (String category : body.getCategories()) {
			
			ProductCategory prodCategory = productCategoryRepository.findByName(category);
			
			if(prodCategory != null) {
				
				listReturn.add(prodCategory);
				
			} else {
				
				throw new BadRequestException();
				
			}
			
		}
		
		return listReturn;
		
	}

	private void saveProduct(ProductModel body) {
		
		Product product = new Product();
		product.setCreationDate(new Date());
		product.setCategories(findCategories(body));
		product.setDescription(body.getDescription());
		product.setName(body.getName());
		repository.save(product);
		
	}
}

