package com.lokesh.springboot.supplychain.service;

import java.util.List;

import com.lokesh.springboot.supplychain.entities.Order;
import com.lokesh.springboot.supplychain.entities.OrderItem;

public interface OrderItemService {
	
	public List<OrderItem> getOrderItemsList();
	
	public OrderItem getOrderItemById(Long id);
	
	public void addOrderItem(OrderItem orderItem);
	
	public void updateOrderItem(OrderItem orderItem);
	
	public void deleteOrderItem(Long id);
	
	public List<OrderItem> getOrderItemsByQuantityBetween(int minQuantity,int maxQuantity);
	
	public List<OrderItem> getOrderItemsByTotalPriceBetween(double lower,double upper);
	
	public List<OrderItem> getOrderItemsByOrderId(Long orderId);
	
	public List<OrderItem> getOrderItemsByProductId(Long productId);

	public List<OrderItem> getOrderItemsByOrder(Order order);
	
}
