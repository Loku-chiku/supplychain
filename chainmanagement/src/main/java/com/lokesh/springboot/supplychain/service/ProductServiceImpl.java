package com.lokesh.springboot.supplychain.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lokesh.springboot.supplychain.entities.Product;
import com.lokesh.springboot.supplychain.repos.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());
	
	@Autowired
	private ProductRepository repo;

	@Override
	public List<Product> getProductsList() {
		try {
			List<Product> products = repo.findAll();
			LOGGER.info("Retrieved list of products");
			return products;
			
			} 
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving list of products", e);
			throw new RuntimeException("Error retrieving list of products:"+ e.getMessage());
		}
			
	}

	@Override
	public Product getProductById(Long id) {
		try {
			Optional<Product> product = repo.findById(id);
			if (product.isPresent()) {
                LOGGER.info("Retrieved product with ID: " + id);
                return product.get();
            } else {
                LOGGER.warning("Product not found with ID: " + id);
                throw new RuntimeException("Product not found with ID: " + id);
            }
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving product with ID: " + id, e);
			throw new RuntimeException("Error retrieving product with ID "+id+": "+e.getMessage());
		}
	}

	@Override
	public void addProduct(Product product) {
		try {
			repo.save(product);
			LOGGER.info("Added new product: " + product.getProductId());
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Error occurred while adding product", e);
			throw new RuntimeException("Error adding product: "+e.getMessage());
		}
	}

	@Override
	public void updateProduct(Product product) {
		try {
			repo.save(product);
			LOGGER.info("Updated product: " + product.getProductId());
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Error occurred while updating product", e);
			throw new RuntimeException("Error updating product: "+e.getMessage());
		}
		
	}

	@Override
	public void deleteProduct(Long id) {
		try {
			repo.deleteById(id);
			LOGGER.info("Deleted product with ID: " + id);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while deleting product with ID: " + id, e);
			throw new RuntimeException("Error deleting product with ID "+id+": "+e.getMessage());
		}
		
	}

	@Override
	public List<Product> getByPrice(double lower,double upper) {
		try{
			return repo.findByProductPriceBetween(lower,upper);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving products between lower and upper prices", e);
			throw new RuntimeException("Error retrieving products between lower and upper prices: " + e.getMessage());
		}
	}

	@Override
	public List<Product> getProductByName(String name) {
		try{
			return repo.findByProductName(name);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving products by name", e);
			throw new RuntimeException("Error retrieving products by name: " + e.getMessage());
		}
	}

	

}
