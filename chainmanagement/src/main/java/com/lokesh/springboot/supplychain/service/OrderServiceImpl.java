package com.lokesh.springboot.supplychain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lokesh.springboot.supplychain.entities.Order;
import com.lokesh.springboot.supplychain.entities.OrderItem;
import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.repos.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class.getName());
	
	@Autowired
	private OrderRepository repo;

	@Override
	public List<Order> getOrdersList() {
		try {
			List<Order> orders = repo.findAll();
			LOGGER.info("Retrieved list of orders");
			return orders;
			
			} 
		catch(Exception e) {
				LOGGER.log(Level.SEVERE, "Error occurred while retrieving list of orders", e);
				throw new RuntimeException("Error retrieving list of orders:"+ e.getMessage());
			}
		
	}

	@Override
	public Order getOrderById(Long id) {
		try {
            Optional<Order> order = repo.findById(id);
            if (order.isPresent()) {
                LOGGER.info("Retrieved order with ID: " + id);
                return order.get();
            } else {
                LOGGER.warning("Order not found with ID: " + id);
                throw new RuntimeException("Order not found with ID: " + id);
            }
        }
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving order with ID: " + id, e);
			throw new RuntimeException("Error retrieving order with ID "+id+": "+e.getMessage());
		}
	}

	@Override
	public void addOrder(Order order) {
		try {
			repo.save(order);
			LOGGER.info("Added new order: " + order.getOrderId());
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Error occurred while adding order", e);
			throw new RuntimeException("Error adding order: "+e.getMessage());
		}
	}

	@Override
	public void updateOrder(Order order) {
		try {
			repo.save(order);
			LOGGER.info("Updated order: " + order.getOrderId());
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Error occurred while updating order", e);
			throw new RuntimeException("Error updating order: "+e.getMessage());
		}		
	}

	@Override
	public void deleteOrder(Long id) {
		try {
			repo.deleteById(id);
			LOGGER.info("Deleted order with ID: " + id);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while deleting order with ID: " + id, e);
			throw new RuntimeException("Error deleting order with ID "+id+": "+e.getMessage());
		}		
	}

	@Override
	public List<Order> getOrdersByStatus(String orderStatus) {
		
		try{
			return repo.findByOrderStatus(orderStatus);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving orders by status", e);
			throw new RuntimeException("Error retrieving orders by status: " + e.getMessage());
		}
	}

	@Override
	public List<Order> getOrdersByDate(LocalDate orderDate) {
		
		try{
			return repo.findByOrderDate(orderDate);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving orders by date", e);
			throw new RuntimeException("Error retrieving orders by date: " + e.getMessage());
		}
	}
	
	@Override
	public List<Order> getOrderByUser(User user) {
		try{
			return repo.findByUser(user);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving orders by user ID", e);
			throw new RuntimeException("Error retrieving orders by user id: " + e.getMessage());
		}
	}

	@Override
	public Order createOrder(User user) {
		try{
			Order order = new Order();
			order.setOrderDate(LocalDate.now());
			order.setOrderStatus("Pending");
			order.setUser(user);
			LOGGER.info("Created new order for user: " + user.getUserName());
			return repo.save(order);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while creating order", e);
			throw new RuntimeException("Error creating orders: " + e.getMessage());
		}
	}

	@Override
	public void updateOrderStatus(Order order, String status) {
		try{
			order.setOrderStatus(status);
			repo.save(order);
			LOGGER.info("Updated order status: " + order.getOrderId() + ", new status: " + status);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while updating order status", e);
			throw new RuntimeException("Error updating orderStatus: " + e.getMessage());
		}
		
	}

	@Override
	public void addItemToOrder(OrderItem orderItem) {
		try{
			Order order = orderItem.getOrder();
			order.getOrderItems().add(orderItem);
			repo.save(order);
			LOGGER.info("Added item to order: " + order.getOrderId() + ", item ID: " + orderItem.getOrderItemId());
		}
		catch(Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding items to order", e);
			throw new RuntimeException("Error adding items to order: " + e.getMessage());
		}
		
		
	}

	@Override
	public List<Order> getOrdersByUserRole(String role) {
		try {
			return repo.findByUserRole(role);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving orders by user role", e);
			throw new RuntimeException("Error retriving orders by user role: " + e.getMessage());
		}
		
	}
	
	

}
