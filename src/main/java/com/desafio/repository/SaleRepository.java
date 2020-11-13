package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.models.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
	
}