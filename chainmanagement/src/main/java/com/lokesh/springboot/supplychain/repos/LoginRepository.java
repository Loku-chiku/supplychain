package com.lokesh.springboot.supplychain.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lokesh.springboot.supplychain.entities.User;

public interface LoginRepository extends JpaRepository<User, Long> {
	
	User findByUserName(String userName);
	
	User findByEmail(String email);

}
