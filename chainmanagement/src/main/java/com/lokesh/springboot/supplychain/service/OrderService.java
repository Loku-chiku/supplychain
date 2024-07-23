package com.lokesh.springboot.supplychain.service;

import java.time.LocalDate;
import java.util.List;

import com.lokesh.springboot.supplychain.entities.Order;
import com.lokesh.springboot.supplychain.entities.OrderItem;
import com.lokesh.springboot.supplychain.entities.User;

public interface OrderService {
	
	public List<Order> getOrdersList();
	
	public Order getOrderById(Long id);
	
	public Order createOrder(User user);
	
	public void updateOrderStatus(Order order,String status);
	
	public void addItemToOrder(OrderItem orderItem);
	
	public void addOrder(Order order);
	
	public void updateOrder(Order order);
	
	public void deleteOrder(Long id);
	
	public List<Order> getOrdersByStatus(String orderStatus);
	
	public List<Order> getOrdersByDate(LocalDate orderDate);
	
	public List<Order> getOrderByUser(User user);
	
	public List<Order> getOrdersByUserRole(String role);	
}
