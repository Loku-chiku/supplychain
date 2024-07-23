package com.lokesh.springboot.supplychain.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lokesh.springboot.supplychain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUserName(String name);
	
	List<User> findByEmail(String email);
	
	List<User> findByRole(String roleName);
	
}
