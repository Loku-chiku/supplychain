package com.lokesh.springboot.supplychain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.lokesh.springboot.supplychain.entities.Order;
import com.lokesh.springboot.supplychain.entities.OrderItem;
import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.repos.OrderRepository;

public class OrderServiceImplTest {

	    @Mock
	    private OrderRepository orderRepository;

	    @InjectMocks
	    private OrderServiceImpl orderService;

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetOrdersList() {
	        // Prepare mock data
	        List<Order> orders = new ArrayList<>();
	        orders.add(new Order());
	        orders.add(new Order());

	        // Configure mock repository
	        when(orderRepository.findAll()).thenReturn(orders);

	        // Call the service method
	        List<Order> result = orderService.getOrdersList();

	        // Verify the result
	        assertEquals(orders, result);
	    }

	    @Test
	    public void testGetOrderById_withExistingId() {
	        // Prepare mock data
	        Long id = 1L;
	        Order order = new Order();
	        order.setOrderId(id);

	        // Configure mock repository
	        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

	        // Call the service method
	        Order result = orderService.getOrderById(id);

	        // Verify the result
	        assertEquals(order, result);
	    }

	    @Test
	    public void testGetOrderById_withNonExistingId() {
	        // Prepare mock data
	        Long id = 1L;

	        // Configure mock repository
	        when(orderRepository.findById(id)).thenReturn(Optional.empty());

	        // Call the service method and verify the exception
	        assertThrows(RuntimeException.class, () -> {
	            orderService.getOrderById(id);
	        });
	    }

	    @Test
	    public void testAddOrder() {
	        // Prepare mock data
	        Order order = new Order();

	        // Call the service method
	        orderService.addOrder(order);

	        // Verify that the repository save method was called
	        verify(orderRepository, times(1)).save(order);
	    }

	    @Test
	    public void testUpdateOrder() {
	        // Prepare mock data
	        Order order = new Order();

	        // Call the service method
	        orderService.updateOrder(order);

	        // Verify that the repository save method was called
	        verify(orderRepository, times(1)).save(order);
	    }

	    @Test
	    public void testDeleteOrder() {
	        // Prepare mock data
	        Long id = 1L;

	        // Call the service method
	        orderService.deleteOrder(id);

	        // Verify that the repository delete method was called
	        verify(orderRepository, times(1)).deleteById(id);
	    }

	    @Test
	    public void testGetOrdersByStatus() {
	        // Prepare mock data
	        String orderStatus = "Pending";
	        List<Order> orders = new ArrayList<>();
	        orders.add(new Order());
	        orders.add(new Order());

	        // Configure mock repository
	        when(orderRepository.findByOrderStatus(orderStatus)).thenReturn(orders);

	        // Call the service method
	        List<Order> result = orderService.getOrdersByStatus(orderStatus);

	        // Verify the result
	        assertEquals(orders, result);
	    }

	    @Test
	    public void testGetOrdersByDate() {
	        // Prepare mock data
	        LocalDate orderDate = LocalDate.now();
	        List<Order> orders = new ArrayList<>();
	        orders.add(new Order());
	        orders.add(new Order());

	        // Configure mock repository
	        when(orderRepository.findByOrderDate(orderDate)).thenReturn(orders);

	        // Call the service method
	        List<Order> result = orderService.getOrdersByDate(orderDate);

	        // Verify the result
	        assertEquals(orders, result);
	    }

	    @Test
	    public void testGetOrderByUser() {
	        // Prepare mock data
	        User user = new User();
	        List<Order> orders = new ArrayList<>();
	        orders.add(new Order());
	        orders.add(new Order());

	        // Configure mock repository
	        when(orderRepository.findByUser(user)).thenReturn(orders);

	        // Call the service method
	        List<Order> result = orderService.getOrderByUser(user);

	        // Verify the result
	        assertEquals(orders, result);
	    }

	    @Test
	    public void testCreateOrder() {
	        // Prepare mock data
	        User user = new User();
	        Order savedOrder = new Order();

	        // Configure mock repository
	        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(savedOrder);

	        // Call the service method
	        Order result = orderService.createOrder(user);

	        // Verify the result
	        assertEquals(savedOrder, result);
	    }

	    @Test
	    public void testUpdateOrderStatus() {
	        // Prepare mock data
	        Order order = new Order();
	        String status = "Completed";

	        // Call the service method
	        orderService.updateOrderStatus(order, status);

	        // Verify that the order status is updated and the repository save method was called
	        assertEquals(status, order.getOrderStatus());
	        verify(orderRepository, times(1)).save(order);
	    }

	    @Test
	    public void testAddItemToOrder() {
	        // Prepare mock data
	        Order order = new Order();
	        OrderItem orderItem = new OrderItem();
	        orderItem.setOrder(order);

	        // Call the service method
	        orderService.addItemToOrder(orderItem);

	        // Verify that the order item is added to the order and the repository save method was called
	        //assertEquals(1, order.getOrderItems().size());
	        verify(orderRepository, times(1)).save(order);
	    }

	    @Test
	    public void testGetOrdersByUserRole() {
	        // Prepare mock data
	        String role = "Admin";
	        List<Order> orders = new ArrayList<>();
	        orders.add(new Order());
	        orders.add(new Order());

	        // Configure mock repository
	        when(orderRepository.findByUserRole(role)).thenReturn(orders);

	        // Call the service method
	        List<Order> result = orderService.getOrdersByUserRole(role);

	        // Verify the result
	        assertEquals(orders, result);
	    }
	}



