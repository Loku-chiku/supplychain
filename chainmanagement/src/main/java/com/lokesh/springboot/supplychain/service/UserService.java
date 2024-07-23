package com.lokesh.springboot.supplychain.service;

import java.util.List;

import com.lokesh.springboot.supplychain.entities.User;

public interface UserService {
	
	public List<User> getUsersList();
	
	public User getUserById(Long id);
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(Long id);
	
	public List<User> getUsersByName(String name);
	
	public List<User> getUsersByEmail(String email);
	
	public List<User> getUsersByRoleName(String roleName);

}
