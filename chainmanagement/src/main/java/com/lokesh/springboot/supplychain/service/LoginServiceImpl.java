package com.lokesh.springboot.supplychain.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.repos.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService{
	
	private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class.getName());
	
	@Autowired
	private LoginRepository repo;

	@Override
	public void saveUser(User user) {
		repo.save(user);
		LOGGER.info("User saved: " + user.getUserName());
		
	}

	@Override
	public boolean authentication(String userName, String password) {
		try{
			User user = repo.findByUserName(userName);
			if (user != null && user.getPassword().equals(password)) {
				LOGGER.info("Authentication successful for user: " + userName);
				return true;
			}
			LOGGER.warning("Authentication failed for user: " + userName);
			return false;
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred during user authentication", e);
			throw new RuntimeException("Error authenticate the user: " + e.getMessage());
		}
		
	}

	@Override
	public String getUserByRole(String userName) {
		try{
			User user = repo.findByUserName(userName);
		
	        if (user != null) {
	        	LOGGER.info("Retrieved user by role: " + userName);
	            return user.getRole();
	        }
	        LOGGER.warning("User not found for username: " + userName);
	        return null;
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving user by role", e);
			throw new RuntimeException("Error retrieving users by role: " + e.getMessage());
		}
		
	}

	@Override
	public User getUserByUserName(String userName) {
		try {
			User user = repo.findByUserName(userName);
			if (user != null) {
                LOGGER.info("Retrieved user by username: " + userName);
            } else {
                LOGGER.warning("User not found for username: " + userName);
            }
            return user;
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving user by username", e);
			throw new RuntimeException("Error retrieving users by userName: " + e.getMessage());
		}
	}

}
