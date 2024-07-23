package com.lokesh.springboot.supplychain.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lokesh.springboot.supplychain.entities.Order;
import com.lokesh.springboot.supplychain.entities.OrderItem;
import com.lokesh.springboot.supplychain.entities.Product;
import com.lokesh.springboot.supplychain.entities.User;
import com.lokesh.springboot.supplychain.service.OrderItemService;
import com.lokesh.springboot.supplychain.service.OrderService;
import com.lokesh.springboot.supplychain.service.ProductService;
import com.lokesh.springboot.supplychain.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
	
	private static final Logger LOGGER = Logger.getLogger(CustomerController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    
	@Autowired
    private OrderItemService orderItemService;
    
    @Autowired
    private HttpSession session;

    @GetMapping("/customer/dashboard")
    public String customerDashboard(ModelMap model) {
    	try{
    		User user = (User) session.getAttribute("user");
    		model.addAttribute("user", user);
    		LOGGER.info("Customer dashboard retrieved successfully");
    		return "customerDashboard";
    	}
    	catch(Exception e) {
    		LOGGER.log(Level.SEVERE, "Error retrieving customer dashboard", e);
			throw new RuntimeException("Error retrieving customer dashboard: " + e.getMessage());
		}
    }

    @GetMapping("/customer/products")
    public String viewProducts(ModelMap model) {
        try{
        	List<Product> products = productService.getProductsList();
        	model.addAttribute("products", products);
        	model.addAttribute("orderItem", new OrderItem());
        	LOGGER.info("Product list retrieved successfully");
        	return "productList";
        }
        catch(Exception e) {
        	LOGGER.log(Level.SEVERE, "Error retrieving product list", e);
			throw new RuntimeException("Error retrieving product list: " + e.getMessage());
		}
    }
    
    @GetMapping("/getByProductName")
	public String getByProductName(String name,ModelMap model) {
		try{
			List<Product> products = productService.getProductByName(name);
			model.addAttribute("products", products);
			LOGGER.info("Products searched by product name successfully");
			return "productList";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error searching product by product name", e);
			throw new RuntimeException("Error searching product by product name: " + e.getMessage());
		}
		
	}
	@GetMapping("/getByProductPrice")
	public String getByProductPrice(double lower,double upper,ModelMap model) {
		try{
			List<Product> products = productService.getByPrice(lower,upper);
			model.addAttribute("products", products);
			LOGGER.info("Products searched by product price successfully");
			return "productList";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error searching product by product price", e);
			throw new RuntimeException("Error searching product by product price: " + e.getMessage());
		}
		
		
	}

    @GetMapping("/customer/orders")
    public String viewOrders(ModelMap model) {
        try{
        	User user = (User) session.getAttribute("user");
        	List<Order> orders = orderService.getOrderByUser(user);
        	model.addAttribute("orders", orders);
        	LOGGER.info("Order list retrieved successfully");
        	return "orderList";
        }
        catch(Exception e) {
        	LOGGER.log(Level.SEVERE, "Error retrieving order list", e);
			throw new RuntimeException("Error retrieving order list: " + e.getMessage());
		}
        
    }
    
    @GetMapping("/searchByOrderStatus")
	public String searchOrdersByStatus(String orderStatus, ModelMap model) {
		try{
			List<Order> orders = orderService.getOrdersByStatus(orderStatus);
			model.addAttribute("orders", orders);
			LOGGER.info("Orders searched by order status successfully");
			return "orderList";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error searching order by order status", e);
			throw new RuntimeException("Error searching order by  order status: " + e.getMessage());
		}
	}
	
	@GetMapping("/searchByOrderDate")
	public String searchOrdersbyDate(LocalDate orderDate,ModelMap model) {
		try{
			List<Order> orders = orderService.getOrdersByDate(orderDate);
			model.addAttribute("orders", orders);
			LOGGER.info("Orders searched by order date successfully");
			return "orderList";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error searching order by order date", e);
			throw new RuntimeException("Error searching order by  order date: " + e.getMessage());
		}
	}
    
    
	@PostMapping("/customer/addToCart/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, @RequestParam("quantity") int quantity) {
       
		try{
			User user = (User) session.getAttribute("user");
			Product product = productService.getProductById(productId);

			List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");

			if (orderItems == null) {
				orderItems = new ArrayList<>();
				session.setAttribute("orderItems", orderItems);
			}
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(product);
			orderItem.setQuantity(quantity);
			orderItem.setTotalPrice(product.getProductPrice()*quantity);
			orderItems.add(orderItem);
        
			session.setAttribute("orderItems", orderItems);
			LOGGER.info("Item added to cart successfully");
			return "redirect:/customer/cart";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error adding item to cart", e);
			throw new RuntimeException("Error adding item to cart: " + e.getMessage());
		}
		
    }
    
    
	@GetMapping("/customer/cart")
    public String viewCart(ModelMap model) {
       try { 
        
		List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");
        model.addAttribute("orderItems", orderItems);
        LOGGER.info("Cart items retrieved successfully");
        return "orderItemsList";
       }
       catch(Exception e) {
    	   LOGGER.log(Level.SEVERE, "Error retrieving cart items", e);
    	   throw new RuntimeException("Error retrieving cart items: " + e.getMessage());
		}
       
    }
	
	@GetMapping("/getByOrderItemsTotalPrice")
	public String getByOrderItemsTotalPrice(double lower,double upper,ModelMap model) {
		try{
			List<OrderItem> orderItems = orderItemService.getOrderItemsByTotalPriceBetween(lower, upper);
			model.addAttribute("orderItems", orderItems);
			LOGGER.info("Cart items retrieved by total price successfully");
			return "orderItemsList";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error retrieving cart items by total price", e);
			throw new RuntimeException("Error retrieving cart items by total price: " + e.getMessage());
		}
		
		
	}
	
	@GetMapping("/getByOrderItemsQuantity")
	public String getByOrderItemsQuantity(int minQuantity,int maxQuantity,ModelMap model) {
		try{
			List<OrderItem> orderItems = orderItemService.getOrderItemsByQuantityBetween(minQuantity, maxQuantity);
			model.addAttribute("orderItems", orderItems);
			LOGGER.info("Cart items retrieved by quantity successfully");
			return "orderItemsList";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error retrieving cart items by quantity", e);
			throw new RuntimeException("Error retrieving cart items by quantity: " + e.getMessage());
		}
		
		
	}
    
    
	@PostMapping("/customer/placeOrder")
    public String placeOrder() {
        try{
        	User user = (User) session.getAttribute("user");
        
			List<OrderItem>orderItems = (List<OrderItem>) session.getAttribute("orderItems");
	
			if(orderItems != null && !orderItems.isEmpty()) {
				// Create a new order
				Order order = new Order();
				order.setOrderDate(LocalDate.now());
				order.setOrderStatus("Placed");
				order.setUser(user);
	        
	        
	        for (OrderItem cartItem : orderItems) {
	            OrderItem orderItem = new OrderItem();
	            Product product = cartItem.getProduct();
	            orderItem.setProduct(product);
	            orderItem.setQuantity(cartItem.getQuantity());
	            orderItem.setTotalPrice(product.getProductPrice() * cartItem.getQuantity());
	            order.addOrderItem(orderItem);
	        }
	
	        orderService.addOrder(order);
	        orderItems.clear();
			}
			LOGGER.info("Order placed successfully");
	        return "redirect:/customer/orders";
        }
        catch(Exception e) {
        	LOGGER.log(Level.SEVERE, "Error placing order", e);
			throw new RuntimeException("Error placing order: " + e.getMessage());
		}
        
	}

    @GetMapping("/customer/changePassword")
    public String changePassword(ModelMap model) {
    	LOGGER.info("Change password page accessed");
    	return "changePasswordCustomer";
    }

    @PostMapping("/customer/updatePassword")
    public String updatePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword) {
    	try{
    		User user = (User) session.getAttribute("user");
	        if(user.getPassword().equals(currentPassword)) {
	        	user.setPassword(newPassword);
	        	userService.updateUser(user);
	        }
	        LOGGER.info("Password updated successfully");
	        return "redirect:/customer/changePassword";
	    }
    	catch(Exception e) {
    		LOGGER.log(Level.SEVERE, "Error updating password", e);
			throw new RuntimeException("Error updating password: " + e.getMessage());
		}
        
    	
    }
        

    @GetMapping("/customer/logout")
    public String logout() {
        // Clear the session and redirect to the login page
        session.invalidate();
        LOGGER.info("User logged out successfully");
        return "redirect:/login";
    }
}
