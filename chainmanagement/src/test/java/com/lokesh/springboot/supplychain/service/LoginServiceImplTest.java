package com.lokesh.springboot.supplychain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.repos.LoginRepository;

@SpringBootTest
public class LoginServiceImplTest {
	

	    @Mock
	    private LoginRepository loginRepository;

	    @InjectMocks
	    private LoginServiceImpl loginService;

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testSaveUser() {
	        User user = new User();
	        loginService.saveUser(user);
	        Mockito.verify(loginRepository, Mockito.times(1)).save(user);
	    }

	    @Test
	    public void testAuthentication_withValidCredentials() {
	        User user = new User();
	        user.setPassword("password");

	        Mockito.when(loginRepository.findByUserName("username")).thenReturn(user);

	        boolean result = loginService.authentication("username", "password");
	        assertTrue(result);
	    }

	    @Test
	    public void testAuthentication_withInvalidCredentials() {
	        Mockito.when(loginRepository.findByUserName("username")).thenReturn(null);

	        boolean result = loginService.authentication("username", "password");
	        assertFalse(result);
	    }

	    @Test
	    public void testGetUserByRole_withExistingUser() {
	        User user = new User();
	        user.setRole("admin");

	        Mockito.when(loginRepository.findByUserName("username")).thenReturn(user);

	        String result = loginService.getUserByRole("username");
	        assertEquals("admin", result);
	    }

	    @Test
	    public void testGetUserByRole_withNonExistingUser() {
	        Mockito.when(loginRepository.findByUserName("username")).thenReturn(null);

	        String result = loginService.getUserByRole("username");
	        assertNull(result);
	    }

	    @Test
	    public void testGetUserByUserName_withExistingUser() {
	        User user = new User();
	        Mockito.when(loginRepository.findByUserName("username")).thenReturn(user);

	        User result = loginService.getUserByUserName("username");
	        assertEquals(user, result);
	    }

	    @Test
	    public void testGetUserByUserName_withNonExistingUser() {
	    	//User user = new User();
	        Mockito.when(loginRepository.findByUserName("username")).thenReturn(null);
	        User result = loginService.getUserByUserName("username");
	        assertEquals(null,result);
	        
	        
	        
	    }
	}



