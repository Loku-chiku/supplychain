package com.lokesh.springboot.supplychain.controller;

import java.time.LocalDate;
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
public class DealerController {

	private static final Logger LOGGER = Logger.getLogger(DealerController.class.getName());
	
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

    @GetMapping("/dealer/dashboard")
    public String dealerDashboard(ModelMap model,@ModelAttribute("user") User user) {
    	try{
    		
    		model.addAttribute("user", user);
    		LOGGER.info("Dealer dashboard accessed successfully");
    		return "dealerDashboard";
    	}
    	catch(Exception e) {
    		LOGGER.log(Level.SEVERE, "Error retrieving dealer dashboard", e);
			throw new RuntimeException("Error retrieving dealer dashboard: " + e.getMessage());
		}
    }


    
    @GetMapping("/dealer/products")
    public String viewProducts(ModelMap model) {
        try{
        	List<Product> products = productService.getProductsList();
            model.addAttribute("products", products);
            LOGGER.info("Product list retrieved successfully");
            return "productListDealer";
        }
        catch(Exception e) {
        	LOGGER.log(Level.SEVERE, "Error retrieving product list", e);
			throw new RuntimeException("Error retrieving product list: " + e.getMessage());
		}
        
    }

    @GetMapping("/dealer/addProduct")
    public String addProductForm(ModelMap model) {
        model.addAttribute("product", new Product());
        LOGGER.info("Add product form accessed");
        return "addProductForm";
    }

    @PostMapping("/dealer/products/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        try{
        	productService.addProduct(product);
        	LOGGER.info("Product added successfully");
        	return "redirect:/dealer/products";
        }
        catch(Exception e) {
        	LOGGER.log(Level.SEVERE, "Error adding product", e);
			throw new RuntimeException("Error adding product: " + e.getMessage());
		}
        
    }

    @GetMapping("/dealer/products/edit/{productId}")
    public String editProductForm(@PathVariable("productId") Long productId, ModelMap model) {
        try{
        	Product product = productService.getProductById(productId);
        	model.addAttribute("product", product);
        	LOGGER.info("Edit product form accessed");
        	return "editProductForm";
        }
        catch(Exception e) {
        	LOGGER.log(Level.SEVERE, "Error retrieving product details", e);
			throw new RuntimeException("Error retrieving product details: " + e.getMessage());
		}
        
    }

    
    @GetMapping("/dealer/products/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        try{
        	productService.deleteProduct(productId);
        	LOGGER.info("Product deleted successfully");
        	return "redirect:/dealer/products";
        }
        catch(Exception e) {
        	LOGGER.log(Level.SEVERE, "Error deleting product", e);
			throw new RuntimeException("Error deleting product: " + e.getMessage());
		}
        
    }
    
    @GetMapping("/getByProductNameDealer")
	public String getByProductName(String name,ModelMap model) {
		try{
			List<Product> products = productService.getProductByName(name);
			model.addAttribute("products", products);
			LOGGER.info("Product searched by name successfully");
			return "productListDealer";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error searching product by product name", e);
			throw new RuntimeException("Error searching product by product name: " + e.getMessage());
		}
		
	}
	@GetMapping("/getByProductPriceDealer")
	public String getByProductPrice(double lower,double upper,ModelMap model) {
		try{
			List<Product> products = productService.getByPrice(lower,upper);
			model.addAttribute("products", products);
			LOGGER.info("Product searched by price successfully");
			return "productListDealer";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error searching product by product price", e);
			throw new RuntimeException("Error searching product by product price: " + e.getMessage());
		}
		
		
	}

    @GetMapping("/dealer/orders")
    public String viewOrders(ModelMap model) {
        try{
        	User user = (User) session.getAttribute("user");
	        List<Order> orders = orderService.getOrderByUser(user);
	        model.addAttribute("orders", orders);
	        LOGGER.info("Order list retrieved successfully");
	        return "orderListDealer";
	    }
        catch(Exception e) {
        	LOGGER.log(Level.SEVERE, "Error retrieving order list", e);
			throw new RuntimeException("Error retrieving order list: " + e.getMessage());
		}
        
    }

    @GetMapping("/dealer/orders/{orderId}")
    public String viewOrderDetails(@PathVariable("orderId") Long orderId, ModelMap model) {
        try{
        	Order order = orderService.getOrderById(orderId);
	        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);
	        model.addAttribute("order", order);
	        model.addAttribute("orderItems", orderItems);
	        LOGGER.info("Order details retrieved successfully");
	        return "orderDetailsDealer";
	    }
        catch(Exception e) {
        	LOGGER.log(Level.SEVERE, "Error retrieving order details", e);
			throw new RuntimeException("Error retrieving order details: " + e.getMessage());
		}
        
    }

    @PostMapping("/dealer/orders/updateStatus/{orderId}")
    public String updateOrderStatus(@PathVariable("orderId") Long orderId,
                                    @RequestParam("status") String status) {
        try{
        	Order order = orderService.getOrderById(orderId);
	        order.setOrderStatus(status);
	        orderService.updateOrder(order);
	        LOGGER.info("Order status updated successfully");
	        return "redirect:/dealer/orders";
	    }
        catch(Exception e) {
        	LOGGER.log(Level.SEVERE, "Error updating order status", e);
			throw new RuntimeException("Error updating order status: " + e.getMessage());
		}
        
    }
    
    @GetMapping("/searchByOrderStatusDealer")
	public String searchOrdersByStatus(String orderStatus, ModelMap model) {
		try{
			List<Order> orders = orderService.getOrdersByStatus(orderStatus);
			model.addAttribute("orders", orders);
			LOGGER.info("Order searched by status successfully");
			return "orderListDealer";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error searching order by order status", e);
			throw new RuntimeException("Error searching order by  order status: " + e.getMessage());
		}
	}
	
	@GetMapping("/searchByOrderDateDealer")
	public String searchOrdersbyDate(LocalDate orderDate,ModelMap model) {
		try{
			List<Order> orders = orderService.getOrdersByDate(orderDate);
			model.addAttribute("orders", orders);
			LOGGER.info("Order searched by date successfully");
			return "orderListDealer";
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Error searching order by order date", e);
			throw new RuntimeException("Error searching order by  order date: " + e.getMessage());
		}
	}

    @GetMapping("/dealer/logout")
    public String logout() {
        session.invalidate();
        LOGGER.info("Dealer logged out successfully");
        return "redirect:/login";
    }
}
