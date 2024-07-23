package com.lokesh.springboot.supplychain.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.service.UserService;

import jakarta.servlet.http.HttpSession;

public class AdminControllerTest {
	
	    @Mock
	    private UserService userService;

	    @Mock
	    private HttpSession session;

	    @InjectMocks
	    private AdminController adminController;

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void adminDashboard_ReturnsAdminDashboard() {
	        // Arrange
	        User user = new User();
	        when(session.getAttribute("user")).thenReturn(user);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = adminController.adminDashboard(model);

	        // Assert
	        assertEquals("adminDashboard", result);
	        assertEquals(user, model.get("user"));
	    }

	    @Test
	    void getUsersList_ReturnsUserListPageWithUsers() {
	        // Arrange
	        List<User> users = new ArrayList<>();
	        when(userService.getUsersList()).thenReturn(users);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = adminController.getUsersList(model);

	        // Assert
	        assertEquals("userList", result);
	        assertEquals(users, model.get("users"));
	    }

	    @Test
	    void getUserById_ReturnsUserDetailsPageWithUser() {
	        // Arrange
	        Long id = 1L;
	        User user = new User();
	        when(userService.getUserById(id)).thenReturn(user);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = adminController.getUserById(id, model);

	        // Assert
	        assertEquals("userDetails", result);
	        assertEquals(user, model.get("user"));
	    }

	    @Test
	    void addUserForm_ReturnsAddUserPageWithNewUser() {
	        // Arrange
	        ModelMap model = new ModelMap();

	        // Act
	        String result = adminController.addUserForm(model);

	        // Assert
	        assertEquals("addUser", result);
	        //assertEquals(new User(), model.get("user"));
	    }

	    @Test
	    void addUser_RedirectsToUsersListPage() {
	        // Arrange
	        User user = new User();
	        doNothing().when(userService).addUser(user);

	        // Act
	        String result = adminController.addUser(user, new ModelMap());

	        // Assert
	        assertEquals("redirect:/admin/users", result);
	        verify(userService).addUser(user);
	    }

	    @Test
	    void updateUserForm_ReturnsUpdateUserPageWithUser() {
	        // Arrange
	        Long id = 1L;
	        User user = new User();
	        when(userService.getUserById(id)).thenReturn(user);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = adminController.updateUserForm(id, model);

	        // Assert
	        assertEquals("updateUser", result);
	        assertEquals(user, model.get("user"));
	    }

	    @Test
	    void deleteUser_RedirectsToUsersListPage() {
	        // Arrange
	        Long id = 1L;
	        doNothing().when(userService).deleteUser(id);

	        // Act
	        String result = adminController.deleteUser(id, new ModelMap());

	        // Assert
	        assertEquals("redirect:/admin/users", result);
	        verify(userService).deleteUser(id);
	    }

	    @Test
	    void searchUsersByName_ReturnsUserListPageWithMatchingUsers() {
	        // Arrange
	        String name = "John";
	        List<User> users = new ArrayList<>();
	        when(userService.getUsersByName(name)).thenReturn(users);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = adminController.searchUsersByName(name, model);

	        // Assert
	        assertEquals("userList", result);
	        assertEquals(users, model.get("users"));
	    }

	    @Test
	    void searchUsersByEmail_ReturnsUserListPageWithMatchingUsers() {
	        // Arrange
	        String email = "john@example.com";
	        List<User> users = new ArrayList<>();
	        when(userService.getUsersByEmail(email)).thenReturn(users);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = adminController.searchUsersByEmail(email, model);

	        // Assert
	        assertEquals("userList", result);
	        assertEquals(users, model.get("users"));
	    }

	    @Test
	    void searchUsersByRoleName_ReturnsUserListPageWithMatchingUsers() {
	        // Arrange
	        String roleName = "admin";
	        List<User> users = new ArrayList<>();
	        when(userService.getUsersByRoleName(roleName)).thenReturn(users);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = adminController.searchUsersByRoleName(roleName, model);

	        // Assert
	        assertEquals("userList", result);
	        assertEquals(users, model.get("users"));
	    }

	    @Test
	    void changePassword_ReturnsChangePasswordAdminPage() {
	        // Arrange
	        ModelMap model = new ModelMap();

	        // Act
	        String result = adminController.changePassword(model);

	        // Assert
	        assertEquals("changePasswordAdmin", result);
	    }

	    @Test
	    void updatePassword_WithValidCurrentPassword_UpdatesUserPasswordAndRedirectsToChangePasswordPage() {
	        // Arrange
	        String currentPassword = "oldPassword";
	        String newPassword = "newPassword";
	        User user = new User();
	        user.setPassword(currentPassword);
	        when(session.getAttribute("user")).thenReturn(user);
	        doNothing().when(userService).updateUser(user);

	        // Act
	        String result = adminController.updatePassword(currentPassword, newPassword);

	        // Assert
	        assertEquals("redirect:/admin/changePassword", result);
	        assertEquals(newPassword, user.getPassword());
	        verify(userService).updateUser(user);
	    }

	    @Test
	    void updatePassword_WithInvalidCurrentPassword_DoesNotUpdateUserPasswordAndRedirectsToChangePasswordPage() {
	        // Arrange
	        String currentPassword = "wrongPassword";
	        String newPassword = "newPassword";
	        User user = new User();
	        //model.get("user");
	        user.setPassword("oldPassword");
	        when(session.getAttribute("user")).thenReturn(user);

	        // Act
	        String result = adminController.updatePassword(currentPassword, newPassword);

	        // Assert
	        assertEquals("redirect:/admin/changePassword", result);
	        assertEquals("oldPassword", user.getPassword()); // Password should not change
	        //verify(userService).updateUser(user);
	        verify(userService, Mockito.never()).updateUser(Mockito.any(User.class)); // Verify that updateUser() is never invoked

	    }

	    @Test
	    void logout_InvalidatesSessionAndRedirectsToLoginPage() {
	        // Arrange
	        doNothing().when(session).invalidate();

	        // Act
	        String result = adminController.logout();

	        // Assert
	        assertEquals("redirect:/login", result);
	        verify(session).invalidate();
	    }
	}



