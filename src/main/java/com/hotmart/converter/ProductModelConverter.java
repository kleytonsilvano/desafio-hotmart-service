package com.hotmart.converter;

import java.util.ArrayList;
import java.util.List;

import com.hotmart.models.Product;
import com.hotmart.models.ProductCategory;

import gen.models.ProductModel;

public class ProductModelConverter implements Converter<ProductModel, Product>{

	@Override
	public ProductModel converter(Product obj) {

		ProductModel returnProductModel = new ProductModel();
		returnProductModel
			.description(obj.getDescription())
			.name(obj.getName())
			.categories(new ArrayList<>());
		
		for (ProductCategory productCategory : obj.getCategories()) {
			returnProductModel.addCategoriesItem(productCategory.getName());
		}

		return returnProductModel;
	}


	public List<ProductModel> converter(List<Product> list) {

		if(list != null) {

			List<ProductModel> listReturn = new ArrayList<>();

			for (Product product : list) {
				listReturn.add(converter(product));
			}

			return listReturn;

		}

		return null;
	}

}
