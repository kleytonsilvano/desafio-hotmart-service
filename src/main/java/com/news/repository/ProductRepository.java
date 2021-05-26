package com.news.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.news.models.db.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Cacheable("productsByName")
	public Page<Product> findAllByOrderByNameAsc(Pageable pageable);
	
	@Cacheable("productsByCategories")
	public Page<Product> findAllByOrderByCategoriesNameAsc(Pageable pageable);
	
	
}