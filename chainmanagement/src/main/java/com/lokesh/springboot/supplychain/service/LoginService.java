package com.lokesh.springboot.supplychain.service;

import com.lokesh.springboot.supplychain.entities.User;

public interface LoginService {
	
	public void saveUser(User user);
	
	public boolean authentication(String userName,String password);
	
	public String getUserByRole(String userName);
	
	public User getUserByUserName(String userName); 
}
