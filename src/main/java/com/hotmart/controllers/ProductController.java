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

import com.hotmart.exceptions.BadRequestException;
import com.hotmart.models.Product;
import com.hotmart.models.ProductCategory;
import com.hotmart.repository.ProductCategoryRepository;
import com.hotmart.repository.ProductRepository;
import com.hotmart.utils.ClassConverter;

import gen.api.ProductsApi;
import gen.models.ProductCreationModel;
import gen.models.ProductModel;

@RestController
public class ProductController implements ProductsApi {
	
	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Override
	public ResponseEntity<Void> addProduct(@Valid @RequestBody ProductCreationModel body) {
		
    	if( body != null ) {

    		List<ProductCategory> categories = findCategories(body);
    		saveProduct(body, categories);
    		
    		return new ResponseEntity<>(HttpStatus.CREATED);
    		
    	}

    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

	private List<ProductCategory> findCategories(ProductCreationModel body) {
		
		List<ProductCategory> listReturn = new ArrayList<>();
		
		for (String category : body.getCategories()) {
			
			ProductCategory prodCategory = productCategoryRepository.findByName(category);
			
			if(prodCategory != null) {
				
				throw new BadRequestException();
				
			} else {
				
				listReturn.add(prodCategory);
				
			}
			
		}
		
		return listReturn;
		
	}

	private void saveProduct(ProductCreationModel body, List<ProductCategory> categories) {
		Product product = (Product) ClassConverter.copyProperties(new Product(), body.getProduct());
		product.setCreationDate(new Date());
		product.setCategories(categories);
		repository.save(product);
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

