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

import com.lokesh.springboot.supplychain.entities.Order;
import com.lokesh.springboot.supplychain.entities.OrderItem;
import com.lokesh.springboot.supplychain.entities.Product;
import com.lokesh.springboot.supplychain.repos.OrderItemRepository;

public class OrderItemServiceImplTest {

	    @Mock
	    private OrderItemRepository orderItemRepository;

	    @InjectMocks
	    private OrderItemServiceImpl orderItemService;

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetOrderItemsList() {
	        // Prepare mock data
	        List<OrderItem> orderItems = new ArrayList<>();
	        orderItems.add(new OrderItem());
	        orderItems.add(new OrderItem());

	        // Configure mock repository
	        when(orderItemRepository.findAll()).thenReturn(orderItems);

	        // Call the service method
	        List<OrderItem> result = orderItemService.getOrderItemsList();

	        // Verify the result
	        assertEquals(orderItems, result);
	    }

	    @Test
	    public void testGetOrderItemById_withExistingId() {
	        // Prepare mock data
	        Long id = 1L;
	        OrderItem orderItem = new OrderItem();
	        orderItem.setOrderItemId(id);

	        // Configure mock repository
	        when(orderItemRepository.findById(id)).thenReturn(Optional.of(orderItem));

	        // Call the service method
	        OrderItem result = orderItemService.getOrderItemById(id);

	        // Verify the result
	        assertEquals(orderItem, result);
	    }

	    @Test
	    public void testGetOrderItemById_withNonExistingId() {
	        // Prepare mock data
	        Long id = 1L;

	        // Configure mock repository
	        when(orderItemRepository.findById(id)).thenReturn(Optional.empty());

	        // Call the service method and verify the exception
	        assertThrows(RuntimeException.class, () -> {
	            orderItemService.getOrderItemById(id);
	        });
	    }

	    @Test
	    public void testAddOrderItem() {
	        // Prepare mock data
	        OrderItem orderItem = new OrderItem();

	        // Call the service method
	        orderItemService.addOrderItem(orderItem);

	        // Verify that the repository save method was called
	        verify(orderItemRepository, times(1)).save(orderItem);
	    }

	    @Test
	    public void testUpdateOrderItem() {
	        // Prepare mock data
	        OrderItem orderItem = new OrderItem();

	        // Call the service method
	        orderItemService.updateOrderItem(orderItem);

	        // Verify that the repository save method was called
	        verify(orderItemRepository, times(1)).save(orderItem);
	    }

	    @Test
	    public void testDeleteOrderItem() {
	        // Prepare mock data
	        Long id = 1L;

	        // Call the service method
	        orderItemService.deleteOrderItem(id);

	        // Verify that the repository delete method was called
	        verify(orderItemRepository, times(1)).deleteById(id);
	    }

	    @Test
	    public void testGetOrderItemsByQuantityBetween() {
	        // Prepare mock data
	        int minQuantity = 1;
	        int maxQuantity = 10;
	        List<OrderItem> orderItems = new ArrayList<>();
	        orderItems.add(new OrderItem());
	        orderItems.add(new OrderItem());

	        // Configure mock repository
	        when(orderItemRepository.findByQuantityBetween(minQuantity, maxQuantity)).thenReturn(orderItems);

	        // Call the service method
	        List<OrderItem> result = orderItemService.getOrderItemsByQuantityBetween(minQuantity, maxQuantity);

	        // Verify the result
	        assertEquals(orderItems, result);
	    }

	    @Test
	    public void testGetOrderItemsByTotalPriceBetween() {
	        // Prepare mock data
	        double lower = 10.0;
	        double upper = 100.0;
	        List<OrderItem> orderItems = new ArrayList<>();
	        orderItems.add(new OrderItem());
	        orderItems.add(new OrderItem());

	        // Configure mock repository
	        when(orderItemRepository.findByTotalPriceBetween(lower, upper)).thenReturn(orderItems);

	        // Call the service method
	        List<OrderItem> result = orderItemService.getOrderItemsByTotalPriceBetween(lower, upper);

	        // Verify the result
	        assertEquals(orderItems, result);
	    }

	    @Test
	    public void testGetOrderItemsByOrderId() {
	        // Prepare mock data
	        Long orderId = 1L;
	        List<OrderItem> orderItems = new ArrayList<>();
	        orderItems.add(new OrderItem());
	        orderItems.add(new OrderItem());

	        // Configure mock repository
	        when(orderItemRepository.findByOrderOrderId(orderId)).thenReturn(orderItems);

	        // Call the service method
	        List<OrderItem> result = orderItemService.getOrderItemsByOrderId(orderId);

	        // Verify the result
	        assertEquals(orderItems, result);
	    }

	    @Test
	    public void testGetOrderItemsByProductId() {
	        // Prepare mock data
	        Long productId = 1L;
	        List<OrderItem> orderItems = new ArrayList<>();
	        OrderItem orderItem = new OrderItem();
	        orderItem.setProduct(new Product()); // Set a product for the order item
	        orderItems.add(orderItem);

	        // Configure mock repository
	        when(orderItemRepository.findByProductProductId(productId)).thenReturn(orderItems);

	        // Call the service method
	        List<OrderItem> result = orderItemService.getOrderItemsByProductId(productId);

	        // Verify the result
	        assertEquals(orderItems, result);
	    }

	    @Test
	    public void testGetOrderItemsByProductId_withNullProduct() {
	        // Prepare mock data
	        Long productId = 1L;
	        List<OrderItem> orderItems = new ArrayList<>();
	        OrderItem orderItem = new OrderItem();
	        orderItem.setProduct(null); // Set a null product for the order item
	        orderItems.add(orderItem);

	        // Configure mock repository
	        when(orderItemRepository.findByProductProductId(productId)).thenReturn(orderItems);

	        // Call the service method and verify the exception
	        assertThrows(RuntimeException.class, () -> {
	            orderItemService.getOrderItemsByProductId(productId);
	        });
	    }

	    @Test
	    public void testGetOrderItemsByOrder() {
	        // Prepare mock data
	        Order order = new Order();
	        List<OrderItem> orderItems = new ArrayList<>();
	        orderItems.add(new OrderItem());
	        orderItems.add(new OrderItem());

	        // Configure mock repository
	        when(orderItemRepository.findByOrder(order)).thenReturn(orderItems);

	        // Call the service method
	        List<OrderItem> result = orderItemService.getOrderItemsByOrder(order);

	        // Verify the result
	        assertEquals(orderItems, result);
	    }
}



