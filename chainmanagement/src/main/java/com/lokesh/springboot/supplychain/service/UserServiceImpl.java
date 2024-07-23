package com.lokesh.springboot.supplychain.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private UserRepository repo;
	
	@Override
	public List<User> getUsersList() {
		try {
			List<User> users = repo.findAll();
			LOGGER.info("Retrieved list of users");
			return users;
			
			} 
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving list of users", e);
			throw new RuntimeException("Error retrieving list of users:"+ e.getMessage());
		}
	}

	@Override
	public User getUserById(Long id) {
		try {
            Optional<User> user = repo.findById(id);
            if (user.isPresent()) {
                LOGGER.info("Retrieved user with ID: " + id);
                return user.get();
            } else {
                LOGGER.warning("User not found with ID: " + id);
                throw new RuntimeException("User not found with ID: " + id);
            }
        }
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving user with ID: " + id, e);
			throw new RuntimeException("Error retrieving user with ID "+id+": "+e.getMessage());
		}
	}

	@Override
	public void addUser(User user) {
		try {
			repo.save(user);
			LOGGER.info("Added new user: " + user.getUserId());
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Error occurred while adding user", e);
			throw new RuntimeException("Error adding user: "+e.getMessage());
		}
		
	}

	@Override
	public void updateUser(User user) {
		try {
			repo.save(user);
			LOGGER.info("Updated user: " + user.getUserId());
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Error occurred while updating user", e);
			throw new RuntimeException("Error updating user: "+e.getMessage());
		}
	}

	@Override
	public void deleteUser(Long id) {
		try {
			repo.deleteById(id);
			LOGGER.info("Deleted user with ID: " + id);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while deleting user with ID: " + id, e);
			throw new RuntimeException("Error deleting user with ID "+id+": "+e.getMessage());
		}
	}

	@Override
	public List<User> getUsersByName(String name) {
		try{
			return repo.findByUserName(name);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving users by name", e);
			throw new RuntimeException("Error retrieving users by name: " + e.getMessage());
		}
		
	}

	@Override
	public List<User> getUsersByEmail(String email) {
		try{
			return repo.findByEmail(email);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving users by email", e);
			throw new RuntimeException("Error retrieving users by email: " + e.getMessage());
		}
	}

	@Override
	public List<User> getUsersByRoleName(String roleName) {
		try{
			return repo.findByRole(roleName);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error occurred while retrieving users by roleName", e);
			throw new RuntimeException("Error retrieving users by roleName: " + e.getMessage());
		}
		
	}

}
