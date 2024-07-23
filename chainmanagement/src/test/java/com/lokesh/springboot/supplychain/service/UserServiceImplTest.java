package com.lokesh.springboot.supplychain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.repos.UserRepository;

public class UserServiceImplTest {

	    @Mock
	    private UserRepository userRepository;

	    @InjectMocks
	    private UserService userService = new UserServiceImpl();

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetUsersList() {
	        // Prepare mock data
	        List<User> users = new ArrayList<>();
	        users.add(new User());
	        when(userRepository.findAll()).thenReturn(users);

	        // Call the service method
	        List<User> result = userService.getUsersList();

	        // Verify the result
	        assertEquals(users, result);
	        verify(userRepository, times(1)).findAll();
	    }

	    @Test
	    public void testGetUserById() {
	        // Prepare mock data
	        Long userId = 1L;
	        User user = new User();
	        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

	        // Call the service method
	        User result = userService.getUserById(userId);

	        // Verify the result
	        assertEquals(user, result);
	        verify(userRepository, times(1)).findById(userId);
	    }

	    @Test
	    public void testGetUserByIdNotFound() {
	        // Prepare mock data
	        Long userId = 1L;
	        when(userRepository.findById(userId)).thenReturn(Optional.empty());

	        // Call the service method (expected to throw an exception)
	        assertThrows(RuntimeException.class, () -> {
	        	userService.getUserById(userId);
	        });	    }

	    @Test
	    public void testAddUser() {
	        // Prepare mock data
	        User user = new User();

	        // Call the service method
	        userService.addUser(user);

	        // Verify the result
	        verify(userRepository, times(1)).save(user);
	    }

	    @Test
	    public void testUpdateUser() {
	        // Prepare mock data
	        User user = new User();

	        // Call the service method
	        userService.updateUser(user);

	        // Verify the result
	        verify(userRepository, times(1)).save(user);
	    }

	    @Test
	    public void testDeleteUser() {
	        // Prepare mock data
	        Long userId = 1L;

	        // Call the service method
	        userService.deleteUser(userId);

	        // Verify the result
	        verify(userRepository, times(1)).deleteById(userId);
	    }

	    @Test
	    public void testGetUsersByName() {
	        // Prepare mock data
	        String name = "TestUser";
	        List<User> users = new ArrayList<>();
	        users.add(new User());
	        when(userRepository.findByUserName(name)).thenReturn(users);

	        // Call the service method
	        List<User> result = userService.getUsersByName(name);

	        // Verify the result
	        assertEquals(users, result);
	        verify(userRepository, times(1)).findByUserName(name);
	    }

	    @Test
	    public void testGetUsersByEmail() {
	        // Prepare mock data
	        String email = "test@example.com";
	        List<User> users = new ArrayList<>();
	        users.add(new User());
	        when(userRepository.findByEmail(email)).thenReturn(users);

	        // Call the service method
	        List<User> result = userService.getUsersByEmail(email);

	        // Verify the result
	        assertEquals(users, result);
	        verify(userRepository, times(1)).findByEmail(email);
	    }

	    @Test
	    public void testGetUsersByRoleName() {
	        // Prepare mock data
	        String roleName = "ROLE_ADMIN";
	        List<User> users = new ArrayList<>();
	        users.add(new User());
	        when(userRepository.findByRole(roleName)).thenReturn(users);

	        // Call the service method
	        List<User> result = userService.getUsersByRoleName(roleName);

	        // Verify the result
	        assertEquals(users, result);
	        verify(userRepository, times(1)).findByRole(roleName);
	    }
	


}
