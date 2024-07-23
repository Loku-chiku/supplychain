package com.lokesh.springboot.supplychain.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

public class DealerControllerTest {

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
	    private DealerController dealerController;

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void dealerDashboard_ReturnsDealerDashboardPageWithUser() {
	        // Arrange
	        User user = new User();
	        ModelMap model = new ModelMap();

	        // Act
	        String result = dealerController.dealerDashboard(model, user);

	        // Assert
	        assertEquals("dealerDashboard", result);
	        assertEquals(user, model.get("user"));
	    }


	    @Test
	    void viewProducts_ReturnsProductListDealerPageWithProducts() {
	        // Arrange
	        List<Product> products = new ArrayList<>();
	        products.add(new Product());
	        when(productService.getProductsList()).thenReturn(products);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = dealerController.viewProducts(model);

	        // Assert
	        assertEquals("productListDealer", result);
	        assertEquals(products, model.get("products"));
	        verify(productService).getProductsList();
	    }

	    @Test
	    void addProductForm_ReturnsAddProductFormPage() {
	        // Arrange
	        ModelMap model = new ModelMap();

	        // Act
	        String result = dealerController.addProductForm(model);

	        // Assert
	        assertEquals("addProductForm", result);
	        assertNotNull(model.get("product"));
	    }

	    @Test
	    void addProduct_WithValidProduct_RedirectsToViewProductsPage() {
	        // Arrange
	        Product product = new Product();
	        doNothing().when(productService).addProduct(product);

	        // Act
	        String result = dealerController.addProduct(product);

	        // Assert
	        assertEquals("redirect:/dealer/products", result);
	        verify(productService).addProduct(product);
	    }

	    @Test
	    void editProductForm_ReturnsEditProductFormPageWithProduct() {
	        // Arrange
	        Long productId = 1L;
	        Product product = new Product();
	        when(productService.getProductById(productId)).thenReturn(product);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = dealerController.editProductForm(productId, model);

	        // Assert
	        assertEquals("editProductForm", result);
	        assertEquals(product, model.get("product"));
	        verify(productService).getProductById(productId);
	    }

	    @Test
	    void deleteProduct_RedirectsToViewProductsPage() {
	        // Arrange
	        Long productId = 1L;
	        doNothing().when(productService).deleteProduct(productId);

	        // Act
	        String result = dealerController.deleteProduct(productId);

	        // Assert
	        assertEquals("redirect:/dealer/products", result);
	        verify(productService).deleteProduct(productId);
	    }

	    @Test
	    void viewOrders_ReturnsOrderListDealerPageWithOrders() {
	        // Arrange
	        User user = new User();
	        List<Order> orders = new ArrayList<>();
	        orders.add(new Order());
	        when(session.getAttribute("user")).thenReturn(user);
	        when(orderService.getOrderByUser(user)).thenReturn(orders);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = dealerController.viewOrders(model);

	        // Assert
	        assertEquals("orderListDealer", result);
	        assertEquals(orders, model.get("orders"));
	        verify(orderService).getOrderByUser(user);
	    }

	    @Test
	    void viewOrderDetails_ReturnsOrderDetailsDealerPageWithOrderAndOrderItems() {
	        // Arrange
	        Long orderId = 1L;
	        Order order = new Order();
	        List<OrderItem> orderItems = new ArrayList<>();
	        orderItems.add(new OrderItem());
	        when(orderService.getOrderById(orderId)).thenReturn(order);
	        when(orderItemService.getOrderItemsByOrder(order)).thenReturn(orderItems);
	        ModelMap model = new ModelMap();

	        // Act
	        String result = dealerController.viewOrderDetails(orderId, model);

	        // Assert
	        assertEquals("orderDetailsDealer", result);
	        assertEquals(order, model.get("order"));
	        assertEquals(orderItems, model.get("orderItems"));
	        verify(orderService).getOrderById(orderId);
	        verify(orderItemService).getOrderItemsByOrder(order);
	    }

	    @Test
	    void updateOrderStatus_WithValidOrderIdAndStatus_UpdatesOrderStatusAndRedirectsToViewOrdersPage() {
	        // Arrange
	        Long orderId = 1L;
	        String status = "shipped";
	        Order order = new Order();
	        when(orderService.getOrderById(orderId)).thenReturn(order);
	        doNothing().when(orderService).updateOrder(order);

	        // Act
	        String result = dealerController.updateOrderStatus(orderId, status);

	        // Assert
	        assertEquals("redirect:/dealer/orders", result);
	        assertEquals(status, order.getOrderStatus());
	        verify(orderService).updateOrder(order);
	    }

	    @Test
	    void logout_InvalidatesSessionAndRedirectsToLoginPage() {
	        // Arrange

	        // Act
	        String result = dealerController.logout();

	        // Assert
	        assertEquals("redirect:/login", result);
	        verify(session).invalidate();
	    }
	}


