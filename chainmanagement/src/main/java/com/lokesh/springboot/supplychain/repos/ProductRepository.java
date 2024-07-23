package com.lokesh.springboot.supplychain.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lokesh.springboot.supplychain.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByProductName(String name);
	
	List<Product> findByProductPriceBetween(double lower,double upper);
}
