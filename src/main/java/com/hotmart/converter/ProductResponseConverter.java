package com.hotmart.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hotmart.models.db.Category;
import com.hotmart.models.db.Product;
import com.hotmart.models.db.ScoreProduct;
import com.hotmart.repository.ScoreProductRepository;

import gen.models.ProductResponse;

public class ProductResponseConverter implements Converter<ProductResponse, Product>{

	@Autowired
	private ScoreProductRepository scoreProductRepository;
	
	@Override
	public ProductResponse converter(Product product) {
		
		ScoreProduct scoreProduct = scoreProductRepository.findByProduct(product);
		
		ProductResponse response = new ProductResponse();
		response.description(product.getDescription())
				.name(product.getName())
				.categories(new ArrayList<>())
				.id(product.getId())
				.score(scoreProduct != null ? scoreProduct.getScore() : 0.0)
				.creationDate(product.getCreationDate());

		for (Category productCategory : product.getCategories()) {
			response.addCategoriesItem(productCategory.getName());
		}

		return response;
	}


	public List<ProductResponse> converter(List<Product> list) {

		if(list != null) {

			List<ProductResponse> listReturn = new ArrayList<>();

			for (Product product : list) {
				listReturn.add(converter(product));
			}

			return listReturn;

		}

		return null;
	}
}
