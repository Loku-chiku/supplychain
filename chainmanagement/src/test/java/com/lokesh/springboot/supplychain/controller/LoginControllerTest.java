package com.lokesh.springboot.supplychain.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.service.LoginService;
import com.lokesh.springboot.supplychain.validations.Validations;

import jakarta.servlet.http.HttpSession;

public class LoginControllerTest {
	
	@Mock
	private LoginService loginService;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private Validations validations;
	
	@InjectMocks
	private LoginController loginController;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	 @Test
	    void gotoLoginPage_ReturnsLoginPage() {
	        // Act
	        String result = loginController.gotoLoginPage();

	        // Assert
	        assertEquals("login", result);
	    }
	
	@Test
	public void login_ValidCredentialsAndAdminRole_ReturnsAdminDashboard() {
		String userName = "Lokesh123";
		String password = "12345";
	    when(loginService.authentication(userName, password)).thenReturn(true);
	    when(loginService.getUserByRole(userName)).thenReturn("Admin");
	    //User user = new User();
	    //when(loginService.getUserByUserName(userName)).thenReturn(user);
	    ModelMap model = new ModelMap();

	        // Act
	        String result = loginController.login(userName, password, model);

	        // Assert
	        assertEquals("adminDashboard", result);
	        //assertEquals(user, session.getAttribute("user"));
	        assertEquals("Admin", model.get("role"));
	        verify(loginService).authentication(userName, password);
	        verify(loginService).getUserByRole(userName);
	        verify(loginService).getUserByUserName(userName);
	        verifyNoMoreInteractions(loginService);
	        //verifyNoMoreInteractions(session);
	    }

	    @Test
	    public void login_ValidCredentialsAndCustomerRole_ReturnsCustomerDashboard() {
	        // Arrange
	        String userName = "Kumari123";
	        String password = "12345";
	        when(loginService.authentication(userName, password)).thenReturn(true);
	        when(loginService.getUserByRole(userName)).thenReturn("Customer");
	        //User user = new User();
	        //when(loginService.getUserByUserName(userName)).thenReturn(user);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = loginController.login(userName, password, model);

	        // Assert
	        assertEquals("customerDashboard", result);
	        //assertEquals(user, session.getAttribute("user"));
	        assertEquals("Customer", model.get("role"));
	        verify(loginService).authentication(userName, password);
	        verify(loginService).getUserByRole(userName);
	        verify(loginService).getUserByUserName(userName);
	        verifyNoMoreInteractions(loginService);
	        //verifyNoMoreInteractions(session);
	    }

	    @Test
	    public void login_ValidCredentialsAndDealerRole_ReturnsDealerDashboard() {
	        // Arrange
	        String userName = "Bhanu123";
	        String password = "12345";
	        when(loginService.authentication(userName, password)).thenReturn(true);
	        when(loginService.getUserByRole(userName)).thenReturn("Dealer");
	        ModelMap model = new ModelMap();

	        // Act
	        String result = loginController.login(userName, password, model);

	        // Assert
	        assertEquals("dealerDashboard", result);
	        verify(loginService).authentication(userName, password);
	        verify(loginService).getUserByRole(userName);
	        //verify(loginService).getUserByUserName(userName);

	        //verifyNoMoreInteractions(loginService);
	        //verifyNoMoreInteractions(session);
	        assertEquals("Dealer", model.get("role"));
	        verifyNoMoreInteractions(loginService);
	        //verifyNoMoreInteractions(session);
	    }

	    
		@Test
	    public void login_InvalidUserRole_ReturnsLoginPageWithError() {
	        // Arrange
	        String userName = "user";
	        String password = "password";
	        when(loginService.authentication(userName, password)).thenReturn(true);
	        when(loginService.getUserByRole(userName)).thenReturn("InvalidRole");
	        ModelMap model = new ModelMap();

	        // Act
	        String result = loginController.login(userName, password, model);

	        // Assert
	        assertEquals("login", result);
	        assertEquals("Invalid user role", model.get("error"));
	        verify(loginService).authentication(userName, password);
	        verify(loginService).getUserByRole(userName);
	        verifyNoMoreInteractions(loginService);
	        //verifyNoMoreInteractions(session);
	    }

	    @Test
	    public void login_InvalidCredentials_ReturnsLoginPageWithError() {
	        // Arrange
	        String userName = "user";
	        String password = "password";
	        when(loginService.authentication(userName, password)).thenReturn(false);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = loginController.login(userName, password, model);

	        // Assert
	        assertEquals("login", result);
	        assertEquals("Invalid credentials", model.get("error"));
	        verify(loginService).authentication(userName, password);
	        verifyNoMoreInteractions(loginService);
	        //verifyNoMoreInteractions(session);
	    }

	    @Test
	    public void login_ExceptionOccursDuringLogin_ReturnsLoginPageWithError() {
	        // Arrange
	        String userName = "user";
	        String password = "password";
	        when(loginService.authentication(userName, password)).thenThrow(new RuntimeException());
	        ModelMap model = new ModelMap();

	        // Act
	        String result = loginController.login(userName, password, model);

	        // Assert
	        assertEquals("login", result);
	        assertEquals("An error occurred during login", model.get("error"));
	        verify(loginService).authentication(userName, password);
	        verifyNoMoreInteractions(loginService);
	        //verifyNoMoreInteractions(session);
	    }

	    @Test
	    public void showRegistrationPage_ReturnsRegisterPage() {
	        // Arrange
	        ModelMap model = new ModelMap();

	        // Act
	        String result = loginController.showRegistrationPage(model);

	        // Assert
	        assertEquals("register", result);
	        //assertEquals(new User(), model.get("user"));
	        verifyNoMoreInteractions(loginService);
	        //verifyNoMoreInteractions(session);
	    }

	    @Test
	    public void register_ValidUser_ReturnsLoginPageWithSuccessMessage() {
	        // Arrange
	        User user = new User();
	        ModelMap model = new ModelMap();

	        // Act
	        String result = loginController.register(user, model);

	        // Assert
	        assertEquals("login", result);
	        assertEquals(user, model.get("user"));
	        assertEquals("Registration successful!", model.get("successMessage"));
	        verify(loginService).saveUser(user);
	        //verifyNoMoreInteractions(loginService);
	        //verifyNoMoreInteractions(session);
	    }

	    @Test
	    public void register_ExistingUsername_ReturnsRegisterPageWithError() {
	        // Arrange
	        User user = new User();
	        String userName = "user";
	        user.setUserName(userName);
	        when(loginService.getUserByUserName(userName)).thenReturn(user);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = loginController.register(user, model);

	        // Assert
	        assertEquals("register", result);
	        assertEquals("Username already exists", model.get("error"));
	        verify(loginService, never()).saveUser(user);
	        verify(loginService).getUserByUserName(userName);
	        verifyNoMoreInteractions(loginService);
	        //verifyNoMoreInteractions(session);
	    }

	    @Test
	    public void register_ExceptionOccursDuringRegistration_ReturnsRegisterPageWithError() {
	        // Arrange
	        User user = new User();
	        doThrow(new RuntimeException()).when(loginService).saveUser(user);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = loginController.register(user, model);

	        // Assert
	        assertEquals("register", result);
	        assertEquals("An error occurred during registration", model.get("error"));
	        verify(loginService).saveUser(user);
	        //verifyNoMoreInteractions(loginService);
	        //verifyNoMoreInteractions(session);
	    }

	    @Test
	    public void logout_ReturnsLoginPage() {
	        // Act
	        String result = loginController.logout();

	        // Assert
	        assertEquals("login", result);
	    }
	}



