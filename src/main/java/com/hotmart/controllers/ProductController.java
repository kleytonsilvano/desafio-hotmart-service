package com.hotmart.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotmart.converter.ProductModelConverter;
import com.hotmart.converter.ProductResponseConverter;
import com.hotmart.exceptions.BadRequestException;
import com.hotmart.models.db.Category;
import com.hotmart.models.db.Product;
import com.hotmart.repository.CategoryRepository;
import com.hotmart.repository.ProductRepository;

import gen.api.ProductsApi;
import gen.models.ProductModel;
import gen.models.ProductResponse;

@RestController
public class ProductController implements ProductsApi {
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductModelConverter productModelConverter;

	@Autowired
	private ProductResponseConverter productResponseConverter; 
	
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
    public ResponseEntity<List<ProductResponse>> listProducts(@NotNull @Valid @RequestParam(value = "page", required = true) Integer page,
													  				@Valid @RequestParam(value = "size", required = false) Integer size,
													  				@Valid @RequestParam(value = "orderBy", required = false) String orderBy) {
			 
		
    	Page<Product> products = productRepository.findAll(PageRequest.of(page, size != null ? size : this.defaultPageSize));
    	
    	if( products != null && products.hasContent()) {
    		
    		return new ResponseEntity<>(productResponseConverter.converter(products.getContent()), HttpStatus.OK);
    		
    	}
    	
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    }

	@Override
	public ResponseEntity<ProductModel> findProductById(@PathVariable("idProduct") Long idProduct) {
		
		Optional<Product> product = productRepository.findById(idProduct);
    	
    	if(product.isPresent()) {
    		
    		return new ResponseEntity<>(productModelConverter.converter(product.get()), HttpStatus.OK);
    		
    	}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

	@Override
	public ResponseEntity<Void> updateProduct(@Valid @RequestBody ProductModel body) {
		
		Optional<Product> product = productRepository.findById(body.getId());
    	
    	if(product.isPresent()) {
    		
    		Product p = product.get();
    		p.setCategories(findCategories(body));
    		p.setDescription(body.getDescription());
    		p.setName(body.getName());
    		productRepository.save(p);

    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    		
    	}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
	
	@Override
	public ResponseEntity<Void> deleteProduct(@PathVariable("idProduct") Long idProduct) {

    	if(productRepository.existsById(idProduct)) {
    		
    		productRepository.deleteById(idProduct);

    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    		
    	}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
    }

	
	
	private List<Category> findCategories(ProductModel body) {
		
		List<Category> listReturn = new ArrayList<>();
		
		for (String category : body.getCategories()) {
			
			Category prodCategory = categoryRepository.findByName(category);
			
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
		productRepository.save(product);
		
	}
}

