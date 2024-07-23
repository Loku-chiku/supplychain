package com.lokesh.springboot.supplychain.service;

import java.util.List;

import com.lokesh.springboot.supplychain.entities.Product;


public interface ProductService {
	
    public List<Product> getProductsList();
	
	public Product getProductById(Long id);
	
	public void addProduct(Product product);
	
	public void updateProduct(Product product);
	
	public void deleteProduct(Long id);
	
	public List<Product> getProductByName(String name);
	
	public List<Product> getByPrice(double lower,double upper);

}
