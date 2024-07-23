package com.lokesh.springboot.supplychain.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lokesh.springboot.supplychain.entities.Order;
import com.lokesh.springboot.supplychain.entities.OrderItem;
import com.lokesh.springboot.supplychain.repos.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	
	private static final Logger LOGGER = Logger.getLogger(OrderItemServiceImpl.class.getName());

	@Autowired
	private OrderItemRepository repo;
	
	@Override
	public List<OrderItem> getOrderItemsList() {
		try {
			List<OrderItem> orderItems = repo.findAll();
			LOGGER.info("Retrieved list of order items");
			return orderItems;
			
			} catch(Exception e) {
				LOGGER.log(Level.SEVERE, "Error occurred while retrieving list of order items", e);
				throw new RuntimeException("Error retrieving list of orderItems:"+ e.getMessage());
			}
	}

	@Override
	public OrderItem getOrderItemById(Long id) {
		try {
			Optional<OrderItem> orderItem = repo.findById(id);
			if (orderItem.isPresent()) {
                LOGGER.info("Retrieved order item with ID: " + id);
                return orderItem.get();
            } else {
                LOGGER.warning("Order item not found with ID: " + id);
                throw new RuntimeException("Order item not found with ID: " + id);
            }
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving order item with ID: " + id, e);
			throw new RuntimeException("Error retrieving orderItem with ID "+id+": "+e.getMessage());
		}
	}

	@Override
	public void addOrderItem(OrderItem orderItem) {
		try {
			repo.save(orderItem);
			LOGGER.info("Added new order item: " + orderItem.getOrderItemId());
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Error occurred while adding order item", e);
			throw new RuntimeException("Error adding orderItem: "+e.getMessage());
		}
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		try {
			repo.save(orderItem);
			LOGGER.info("Updated order item: " + orderItem.getOrderItemId());
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Error occurred while updating order item", e);
			throw new RuntimeException("Error updating orderItem: "+e.getMessage());
		}
		
	}

	@Override
	public void deleteOrderItem(Long id) {
		try {
			repo.deleteById(id);
			LOGGER.info("Deleted order item with ID: " + id);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while deleting order item with ID: " + id, e);
			throw new RuntimeException("Error deleting orderItem with ID "+id+": "+e.getMessage());
		}
		
	}

	@Override
	public List<OrderItem> getOrderItemsByQuantityBetween(int minQuantity, int maxQuantity) {
		try{
			return repo.findByQuantityBetween(minQuantity, maxQuantity);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving order items by quantity", e);
			throw new RuntimeException("Error retrieving orderItems between maximum quantity and minimum quantity: " + e.getMessage());
		}
	}

	@Override
	public List<OrderItem> getOrderItemsByTotalPriceBetween(double lower, double upper) {
		try{
			return repo.findByTotalPriceBetween(lower, upper);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving order items by total price", e);
			throw new RuntimeException("Error retrieving orderItems between lower and upper prices: " + e.getMessage());
		}
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
		try{
			return repo.findByOrderOrderId(orderId);
		}
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving order items by order ID", e);
			throw new RuntimeException("Error retrieving orderItems by order ID: " + e.getMessage());
		}
	}

	
	@Override
	public List<OrderItem> getOrderItemsByProductId(Long productId) {
		try{
			List<OrderItem> orderItems = repo.findByProductProductId(productId);
			for(OrderItem orderItem : orderItems) {
				if (orderItem.getProduct() == null) {
                    LOGGER.warning("Product is null for OrderItem with ID: " + orderItem.getOrderItemId());
                    throw new RuntimeException("Product is null for OrderItem with ID: " + orderItem.getOrderItemId());
                }
            }
            LOGGER.info("Retrieved order items by product ID: " + productId);
            return orderItems;
		}
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving order items by product ID", e);
			throw new RuntimeException("Error retrieving orderItems by product ID: " + e.getMessage());
		}
	}

	@Override
	public List<OrderItem> getOrderItemsByOrder(Order order) {
		try {
	        return repo.findByOrder(order);
	    } 
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving order items by order", e);
	        throw new RuntimeException("Error retrieving orderItems by order: " + e.getMessage());
	    }
	}

}
