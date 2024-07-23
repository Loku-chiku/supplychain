package com.lokesh.springboot.supplychain.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lokesh.springboot.supplychain.entities.Order;
import com.lokesh.springboot.supplychain.entities.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByOrderStatus(String orderStatus);
	
	List<Order> findByOrderDate(LocalDate orderDate);
	
	List<Order> findByUser(User user);
	
	List<Order> findByUserRole(String role);
	
}
