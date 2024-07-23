package com.lokesh.springboot.supplychain.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	private static final Logger LOGGER = Logger.getLogger(AdminController.class.getName());
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/admin/dashboard")
	public String adminDashboard(ModelMap model) {
		try{
			User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);
			LOGGER.info("Admin dashboard retrieved successfully");
			return "adminDashboard";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE,"Error retrieving admin dashboard",e);
			throw new RuntimeException("Error retrieving admin dashboard: " + e.getMessage());
		}
	}

	@GetMapping("/admin/users")
	public String getUsersList(ModelMap model) {
		try{
			List<User> users = userService.getUsersList();
			model.addAttribute("users", users);
			LOGGER.info("User list retrieved successfully");
			return "userList";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE,"Error retrieving user list",e);
			throw new RuntimeException("Error retrieving user list: " + e.getMessage());
		}
	}
	
	@GetMapping("/user/{id}")
	public String getUserById(@PathVariable("id")Long id,ModelMap model) {
		try{
			User user = userService.getUserById(id);
			model.addAttribute("user", user);
			LOGGER.info("User details retrived successfully");
			return "userDetails";
			
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE,"Error retrieving user details",e);
			throw new RuntimeException("Error retrieving user details: " + e.getMessage());
		}
		
	}
	
	@GetMapping("/addUser")
	public String addUserForm(ModelMap model) {
		model.addAttribute("user", new User());
		LOGGER.info("Add user form loaded");
		return "addUser";
	}
	
	@PostMapping("/admin/addUser")
	public String addUser(@ModelAttribute("user") User user,ModelMap model) {
		try{
			userService.addUser(user);
			LOGGER.info("User added successfully");
			return "redirect:/admin/users";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE,"Error adding user",e);
			throw new RuntimeException("Error adding user: " + e.getMessage());
		}
		
	}
	
	
	
	@GetMapping("user/update/{id}")
	public String updateUserForm(@PathVariable("id") Long id, ModelMap model) {
		try{
			User user = userService.getUserById(id);
			model.addAttribute("user", user);
			LOGGER.info("Update user form loaded");
			return "updateUser";
		}
		catch(Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving user for update", e);
			throw new RuntimeException("Error retrieving user for update: " + e.getMessage());
		}
		
	}
	
	@PostMapping("/user/delete")
	public String deleteUser(@RequestParam("id") Long id,ModelMap model) {
		try{
			userService.deleteUser(id);
			LOGGER.info("User deleted successfully");
			return "redirect:/admin/users";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error deleting user", e);
			throw new RuntimeException("Error deleting user: " + e.getMessage());
		}
		
	}
	
	@GetMapping("/searchByUserName")
	public String searchUsersByName(@RequestParam("name") String name, ModelMap model) {
		try{
			List<User> users = userService.getUsersByName(name);
			model.addAttribute("users", users);
			LOGGER.info("Users searched by user name successfully");
			return "userList";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error searching users by user name", e);
			throw new RuntimeException("Error searching users by user name: " + e.getMessage());
		}
		
	}
	
	@GetMapping("/searchByUserEmail")
	public String searchUsersByEmail(@RequestParam("email") String email, ModelMap model) {
		try{
			List<User> users = userService.getUsersByEmail(email);
			model.addAttribute("users", users);
			LOGGER.info("Users searched by email successfully");
			return "userList";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error searching users by email", e);
			throw new RuntimeException("Error searching users by email: " + e.getMessage());
		}
	}
	
	
	@GetMapping("/searchByUserRoleName")
	public String searchUsersByRoleName(@RequestParam("roleName")String roleName, ModelMap model) {
		try{
			List<User> users = userService.getUsersByRoleName(roleName);
		model.addAttribute("users", users);
		LOGGER.info("Users searched by role name successfully");
		return "userList";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error searching users by role name", e);
			throw new RuntimeException("Error searching users by role name: " + e.getMessage());
		}
		
	}
	
	
    @GetMapping("/admin/changePassword")
    public String changePassword(ModelMap model) {
    	LOGGER.info("Change password form loaded for admin");
    	return "changePasswordAdmin";
    }

    @PostMapping("/admin/updatePassword")
    public String updatePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword) {
        try{
        	User user = (User) session.getAttribute("user");
        	if(user.getPassword().equals(currentPassword)) {
        		user.setPassword(newPassword);
        		userService.updateUser(user);
        	}
        	LOGGER.info("Password updated successfully");
        	return "redirect:/admin/changePassword";
        }
        catch(Exception e) {
        	LOGGER.log(Level.SEVERE, "Error updating password", e);
			throw new RuntimeException("Error updating password: " + e.getMessage());
		}
        
    }
        

    @GetMapping("/admin/logout")
    public String logout() {
        // Clear the session and redirect to the login page
        session.invalidate();
        LOGGER.info("Admin logged out");
        return "redirect:/login";
    }

}
