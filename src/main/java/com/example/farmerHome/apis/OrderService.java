package com.example.farmerHome.apis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.omg.CORBA.INITIALIZE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.farmerHome.entities.Basket;
import com.example.farmerHome.entities.Order;
import com.example.farmerHome.entities.Product;
import com.example.farmerHome.repositories.BasketRepository;
import com.example.farmerHome.repositories.OrderRepository;
//import com.example.farmerHome.repositories.OrderRepository;
import com.example.farmerHome.repositories.ProductRepository;

//package com.example.farmerHome.apis;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Dictionary;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.List;
//import java.util.Set;
//
//import javax.transaction.Transactional;
//import javax.validation.constraints.Max;
//import javax.ws.rs.BeanParam;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.MediaType;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.example.farmerHome.entities.Basket;
//import com.example.farmerHome.entities.Farmer;
//import com.example.farmerHome.entities.Order;
//import com.example.farmerHome.entities.Product;
//import com.example.farmerHome.entities.ProductCategories;
//import com.example.farmerHome.repositories.OrderRepository;
//import com.example.farmerHome.repositories.ProductRepository;
//
@Component //indicate to Spring to create an object of this class as a component
@Scope("singleton") //creates one object per application - Default option
@Path("/order") //map the URL pattern with the class as service
public class OrderService {
	
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BasketService basketService;

	public OrderService() {
		System.out.println("Order service created");
	}

	
	
@POST
@Path("register/{basketId}/{productId}/{quantity}")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public List<Order> registerOrUpdateOrder(@PathParam("basketId") int basketId, 
		@PathParam("productId") int productId, 
		@PathParam("quantity") int quantity) {
	Order order = fetchOrdersByProduct(productId, basketId);
	System.out.println("order id ====" + order);
	
	if(order == null) {	
	Product currentProduct = productService.findByProductId(productId);
	Basket currentBasket = basketService.findByBasketId(basketId);
	
	Order newOrder = new Order();
		
	newOrder.setBasket(currentBasket);
	newOrder.setProduct(currentProduct);
	newOrder.setQuantity(quantity);
	
	order = orderRepository.save(newOrder);
	
	List<Order> orders = findByBasket(basketId);
	
	
	return orders;
	}
	else {
				
	Product currentProduct = productService.findByProductId(productId);
	Basket currentBasket = basketService.findByBasketId(basketId);
			
	order.setBasket(currentBasket);
	order.setProduct(currentProduct);
	order.setQuantity(quantity);
		
	order = orderRepository.save(order);
	
	List<Order> orders = findByBasket(basketId);
	return orders;
	}
}



@GET
@Path("/find/{orderId}")
@Produces(MediaType.APPLICATION_JSON)
@Transactional 
public Order findByOrderId(@PathParam("orderId") int orderId) {
		//fetches product details from DB by productId
		//@PathParam - argument for the method
	try {System.out.println(orderRepository.findById(orderId).get());
		return orderRepository.findById(orderId).get();
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}	
}

	
@GET
@Path("/getOrderByBasket/{basketId}")
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public List<Order> findByBasket(@PathParam("basketId") int basketId) {
	return orderRepository.getOrderByBasket(basketId);
	}
//
//
@POST //HTTP method
@Path("/deleteFromOrder") //URL 
public List<Order> deleteOrder(@FormParam("basketId") int basketId, 
								  @FormParam("productId") int productId) {
		System.out.println("==========" + productId + basketId + "===============");
	try {		
		Order ord = fetchOrdersByProduct(productId, basketId);
		
		System.out.println("==========="+ord+"===========");
		
		ord.setBasket(null);
		ord.setProduct(null);
		
		orderRepository.delete(ord);
		
		List<Order> order = findByBasket(basketId);
		return order;
		
	} catch (Exception e) {
		e.printStackTrace();
	
	return null;}
}


@POST
@Path("/deleteBasket")
public List<Order> deleteByOrderId(@FormParam("basketId") int basketId) {
	List<Order> order = findByBasket(basketId);
	System.out.println(order);
	for (Order ord : order) {
		
		ord.setBasket(null);
		ord.setProduct(null);
		
		orderRepository.delete(ord);
		
	}
	return null;
	
}




@GET
@Path("/fetchByProductId/{productId}/{basketId}")
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public Order fetchOrdersByProduct(@PathParam("productId") Integer productId, @PathParam("basketId") Integer basketId) {
	try {
		
		System.out.println("the current order fetched by product and baket id" + orderRepository.findByProductId(productId, basketId));

		return orderRepository.findByProductId(productId, basketId);
		
	} catch(Exception e) {e.printStackTrace();
	return null;
		}


	}
	
@POST
@Path("/calculateTotal")
public double calculateTotal(@FormParam("basketId") int basketId) {
	List<Order> order = findByBasket(basketId);
	double Total = 0.0;

	for (Order ord : order) {
		
		int quantity = ord.getQuantity();
		Product prod = ord.getProduct();
		double price = prod.getPrice();
		System.out.println("quantity ========" + quantity);
		System.out.println("price ========" + price);
		double SubTotal = quantity*price;
		
		Total = Total + SubTotal;
	}
		
	
	
	return Total; 
}

//@GET
//@Path("/fetchByProductId/{productId}/{basketId}")
//@Produces(MediaType.APPLICATION_JSON)
//@Transactional
//public Integer fetchOrderIdByProduct(@PathParam("productId") Integer productId, @PathParam("basketId") Integer basketId) {
//	try {
//		
//		System.out.println("the current orderId fetched by product and baket id" + orderRepository.getOrderId(productId, basketId));
//
//		int i = orderRepository.getOrderId(productId, basketId);
//		System.out.println("In fetch id = " + i);
//		return i;
//		
//	} catch(Exception e) {e.printStackTrace();
//	System.out.println("Error in fetch Order Id by basket and product");
//	return null;
//		}
//
//
//	}
//	
}

