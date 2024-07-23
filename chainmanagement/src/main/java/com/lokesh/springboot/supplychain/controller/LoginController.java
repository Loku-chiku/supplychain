package com.lokesh.springboot.supplychain.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.service.LoginService;
import com.lokesh.springboot.supplychain.validations.Validations;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
	
		private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
	
		@Autowired
		private LoginService loginService;
		
		@Autowired
		private HttpSession session;
		
		@Autowired
		private Validations validations;
		
		@GetMapping("/login")
		public String gotoLoginPage() {
			LOGGER.info("Navigating to login page");
			return "login";
		}
		
		@PostMapping("/login")
	    public String login(@RequestParam("userName") String userName,
	                        @RequestParam("password") String password,
	                        ModelMap model) {
	        try {
	            boolean isAuthenticated = loginService.authentication(userName, password);
	            if (isAuthenticated) {
	   
	                String role = loginService.getUserByRole(userName);
	                model.addAttribute("role", role);

	                if (role.equalsIgnoreCase("Admin")) {
	                	User user = loginService.getUserByUserName(userName);
		            	session.setAttribute("user", user);
		            	LOGGER.info("Admin logged in successfully");
	                    return "adminDashboard";
	                } else if (role.equalsIgnoreCase("Customer")) {
	                	User user = loginService.getUserByUserName(userName);
		            	session.setAttribute("user", user);
		            	LOGGER.info("Customer logged in successfully");
	                    return "customerDashboard";
	                } else if (role.equalsIgnoreCase("Dealer")) {
	                	LOGGER.info("Dealer logged in successfully");
	                    return "dealerDashboard";
	                } else {
	                    model.addAttribute("error", "Invalid user role");
	                    LOGGER.warning("Invalid user role");
	                    return "login";
	                }
	            } else {
	                model.addAttribute("error", "Invalid credentials");
	                LOGGER.warning("Invalid login credentials");
	                return "login";
	            }
	        }
	        catch (Exception e) {
	            model.addAttribute("error", "An error occurred during login");
	            LOGGER.log(Level.SEVERE, "Error occurred during login", e);
	            return "login";
	        }
	    }

	    @GetMapping("/register")
	    public String showRegistrationPage(ModelMap model) {
	    	model.addAttribute("user", new User());
	    	LOGGER.info("Navigating to registration page");
	        return "register";
	    }

	    @PostMapping("/register")
	    public String register(User user, ModelMap model) {
	        try {
	        	validations.validateFirstName(user.getFirstName());
	        	validations.validateLastName(user.getLastName());
	        	validations.validatePhoneNumber(user.getPhone());
	        	validations.validateEmailAddress(user.getEmail());
	        	if(loginService.getUserByUserName(user.getUserName()) != null) {
	        		model.addAttribute("error", "Username already exists");
	        		LOGGER.warning("Username already exists");
	        		return "register";
	        	}
	            loginService.saveUser(user);
	            model.addAttribute("user", user);
	            model.addAttribute("successMessage", "Registration successful!");
	            LOGGER.info("User registered successfully");
	            return "login";
	            
	        }
	        
	        catch (Exception e) {
	            model.addAttribute("error", "An error occurred during registration");
	            LOGGER.log(Level.SEVERE, "Error occurred during registration", e);
	            return "register";
	        }
	    }

	    @GetMapping("/logout")
	    public String logout() {
	    	LOGGER.info("Logged out");
	        return "login";
	    }
}