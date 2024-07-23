package com.lokesh.springboot.supplychain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lokesh.springboot.supplychain.entities.Product;
import com.lokesh.springboot.supplychain.repos.ProductRepository;

public class ProductServiceImplTest {

	    @Mock
	    private ProductRepository productRepository;

	    @InjectMocks
	    private ProductService productService = new ProductServiceImpl();

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetProductsList() {
	        // Prepare mock data
	        List<Product> products = new ArrayList<>();
	        products.add(new Product());
	        when(productRepository.findAll()).thenReturn(products);

	        // Call the service method
	        List<Product> result = productService.getProductsList();

	        // Verify the result
	        assertEquals(products, result);
	        verify(productRepository, times(1)).findAll();
	    }

	    @Test
	    public void testGetProductById() {
	        // Prepare mock data
	        Long productId = 1L;
	        Product product = new Product();
	        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

	        // Call the service method
	        Product result = productService.getProductById(productId);

	        // Verify the result
	        assertEquals(product, result);
	        verify(productRepository, times(1)).findById(productId);
	    }

	    @Test
	    public void testGetProductByIdNotFound() {
	        // Prepare mock data
	        Long productId = 1L;
	        when(productRepository.findById(productId)).thenReturn(Optional.empty());

	        // Call the service method (expected to throw an exception)
	        assertThrows(RuntimeException.class, () -> {
	        	productService.getProductById(productId);
	        });
	    }

	    @Test
	    public void testAddProduct() {
	        // Prepare mock data
	        Product product = new Product();

	        // Call the service method
	        productService.addProduct(product);

	        // Verify the result
	        verify(productRepository, times(1)).save(product);
	    }

	    @Test
	    public void testUpdateProduct() {
	        // Prepare mock data
	        Product product = new Product();

	        // Call the service method
	        productService.updateProduct(product);

	        // Verify the result
	        verify(productRepository, times(1)).save(product);
	    }

	    @Test
	    public void testDeleteProduct() {
	        // Prepare mock data
	        Long productId = 1L;

	        // Call the service method
	        productService.deleteProduct(productId);

	        // Verify the result
	        verify(productRepository, times(1)).deleteById(productId);
	    }

	    @Test
	    public void testGetByPrice() {
	        // Prepare mock data
	        double lower = 10.0;
	        double upper = 20.0;
	        List<Product> products = new ArrayList<>();
	        products.add(new Product());
	        when(productRepository.findByProductPriceBetween(lower, upper)).thenReturn(products);

	        // Call the service method
	        List<Product> result = productService.getByPrice(lower, upper);

	        // Verify the result
	        assertEquals(products, result);
	        verify(productRepository, times(1)).findByProductPriceBetween(lower, upper);
	    }

	    @Test
	    public void testGetProductByName() {
	        // Prepare mock data
	        String name = "TestProduct";
	        List<Product> products = new ArrayList<>();
	        products.add(new Product());
	        when(productRepository.findByProductName(name)).thenReturn(products);

	        // Call the service method
	        List<Product> result = productService.getProductByName(name);

	        // Verify the result
	        assertEquals(products, result);
	        verify(productRepository, times(1)).findByProductName(name);
	    }
	


}
