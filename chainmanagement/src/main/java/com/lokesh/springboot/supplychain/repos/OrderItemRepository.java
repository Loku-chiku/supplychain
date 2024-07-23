package com.lokesh.springboot.supplychain.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lokesh.springboot.supplychain.entities.Order;
import com.lokesh.springboot.supplychain.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	List<OrderItem> findByQuantityBetween(int minQuantity,int maxQuantity);
	
	List<OrderItem> findByTotalPriceBetween(double lower,double upper);
	
	List<OrderItem> findByOrderOrderId(Long orderId);
	
	List<OrderItem> findByProductProductId(Long productId);

	List<OrderItem> findByOrder(Order order);
}
