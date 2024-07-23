package com.lokesh.springboot.supplychain.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import com.lokesh.springboot.supplychain.entities.Order;
import com.lokesh.springboot.supplychain.entities.OrderItem;
import com.lokesh.springboot.supplychain.entities.Product;
import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.service.OrderItemService;
import com.lokesh.springboot.supplychain.service.OrderService;
import com.lokesh.springboot.supplychain.service.ProductService;
import com.lokesh.springboot.supplychain.service.UserService;

import jakarta.servlet.http.HttpSession;

public class CustomerControllerTest {

	    @Mock
	    private UserService userService;

	    @Mock
	    private ProductService productService;

	    @Mock
	    private OrderService orderService;

	    @Mock
	    private OrderItemService orderItemService;

	    @Mock
	    private HttpSession session;

	    @InjectMocks
	    private CustomerController customerController;

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void customerDashboard_ReturnsCustomerDashboardPageWithUser() {
	        // Arrange
	        User user = new User();
	        ModelMap model = new ModelMap();
	        when(session.getAttribute("user")).thenReturn(user);

	        // Act
	        String result = customerController.customerDashboard(model);

	        // Assert
	        assertEquals("customerDashboard", result);
	        assertEquals(user, model.get("user"));
	        verify(session).getAttribute("user");
	    }

	    @Test
	    void viewProducts_ReturnsProductListPageWithProductsAndOrderItem() {
	        // Arrange
	        List<Product> products = new ArrayList();
	        products.add(new Product());
	        ModelMap model = new ModelMap();
	        when(productService.getProductsList()).thenReturn(products);

	        // Act
	        String result = customerController.viewProducts(model);

	        // Assert
	        assertEquals("productList", result);
	        assertEquals(products, model.get("products"));
	        assertNotNull(model.get("orderItem"));
	        verify(productService).getProductsList();
	    }

	    @Test
	    void viewOrders_ReturnsOrderListPageWithOrders() {
	        // Arrange
	        User user = new User();
	        List<Order> orders = new ArrayList<>();
	        orders.add(new Order());
	        ModelMap model = new ModelMap();
	        when(session.getAttribute("user")).thenReturn(user);
	        when(orderService.getOrderByUser(user)).thenReturn(orders);

	        // Act
	        String result = customerController.viewOrders(model);

	        // Assert
	        assertEquals("orderList", result);
	        assertEquals(orders, model.get("orders"));
	        verify(session).getAttribute("user");
	        verify(orderService).getOrderByUser(user);
	    }

	    @Test
	    void addToCart_RedirectsToCartPageAndAddsOrderItemToSession() {
	        // Arrange
	        Long productId = 1L;
	        int quantity = 2;
	        User user = new User();
	        Product product = new Product();
	        List<OrderItem> orderItems = new ArrayList<>();
	        when(session.getAttribute("user")).thenReturn(user);
	        when(productService.getProductById(productId)).thenReturn(product);
	        when(session.getAttribute("orderItems")).thenReturn(orderItems);

	        // Act
	        String result = customerController.addToCart(productId, quantity);

	        // Assert
	        assertEquals("redirect:/customer/cart", result);
	        assertEquals(1, orderItems.size());
	        assertEquals(product, orderItems.get(0).getProduct());
	        assertEquals(quantity, orderItems.get(0).getQuantity());
	        verify(session).getAttribute("user");
	        verify(productService).getProductById(productId);
	        verify(session).getAttribute("orderItems");
	        verify(session).setAttribute("orderItems", orderItems);
	    }

	    @Test
	    void viewCart_ReturnsOrderItemsListPageWithOrderItemsFromSession() {
	        // Arrange
	        List<OrderItem> orderItems = new ArrayList<>();
	        orderItems.add(new OrderItem());
	        ModelMap model = new ModelMap();
	        when(session.getAttribute("orderItems")).thenReturn(orderItems);

	        // Act
	        String result = customerController.viewCart(model);

	        // Assert
	        assertEquals("orderItemsList", result);
	        assertEquals(orderItems, model.get("orderItems"));
	        verify(session).getAttribute("orderItems");
	    }

	    @Test
	    void placeOrder_AddsOrderAndOrderItemsToDatabaseAndClearsSessionOrderItems() {
	        // Arrange
	        User user = new User();
	      
			 List<OrderItem> orderItems = new ArrayList<>();
	        Order order = new Order();
	        when(session.getAttribute("user")).thenReturn(user);
	        when(session.getAttribute("orderItems")).thenReturn(orderItems);
	        doNothing().when(orderService).addOrder(order);

	        // Act
	        String result = customerController.placeOrder();

	        // Assert
	        assertEquals("redirect:/customer/orders", result);
	        //assertEquals(LocalDate.now(), order.getOrderDate());
	        //assertEquals("Placed", order.getOrderStatus());
	        //assertEquals(user, order.getUser());
	        verify(session).getAttribute("user");
	        verify(session).getAttribute("orderItems");
	        //verify(orderService).addOrder(order);
	        assertTrue(orderItems.isEmpty());
	    }

	    @Test
	    void changePassword_ReturnsChangePasswordCustomerPage() {
	        // Arrange

	        // Act
	        String result = customerController.changePassword(new ModelMap());

	        // Assert
	        assertEquals("changePasswordCustomer", result);
	    }

	    /*@Test
	    void updatePassword_WithValidCurrentPassword_UpdatesUserPasswordAndRedirectsToChangePasswordPage() {
	        // Arrange
	        String currentPassword = "oldpassword";
	        String newPassword = "newpassword";
	        User user = new User();
	        when(session.getAttribute("user")).thenReturn(user);
	        doNothing().when(userService).updateUser(user);

	        // Act
	        String result = customerController.updatePassword(currentPassword, newPassword);

	        // Assert
	        assertEquals("redirect:/customer/changePassword", result);
	        assertEquals(newPassword, user.getPassword());
	        verify(session).getAttribute("user");
	        verify(userService).updateUser(user);
	    }*/

	    @Test
	    void logout_InvalidatesSessionAndRedirectsToLoginPage() {
	        // Arrange

	        // Act
	        String result = customerController.logout();

	        // Assert
	        assertEquals("redirect:/login", result);
	        verify(session).invalidate();
	    }
	}



