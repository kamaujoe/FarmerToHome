package com.example.farmerHome.apis;

import java.util.List;
import java.util.Set;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.farmerHome.entities.Basket;
import com.example.farmerHome.entities.Consumer;
import com.example.farmerHome.entities.Product;
import com.example.farmerHome.repositories.BasketRepository;
import com.example.farmerHome.repositories.ConsumerRepository;
import com.example.farmerHome.repositories.ProductRepository;



@Component
@Scope("singleton")
@Path("/baskets/")
public class BasketService {
	
	@Autowired
	private BasketRepository basketRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	

	public BasketService() {
		System.out.println("Basket Service Created");
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON) // object to be given in json
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Transactional
	public Basket registerOrUpdateBasket(@BeanParam Basket ba) {
		ba = basketRepository.save(ba);
		System.out.println("Basket Registered" + ba);
		return ba;
	}
	
	@GET //HTTP method used to call the api
	@Path("/find/{basketId}")
	@Produces(MediaType.APPLICATION_JSON) 
	@Transactional // help to fetch dependent data
	public Basket findByBasketId(@PathParam("basketId") int basketId) {
			// use the path parameter as the argument for the method
		try {
			Basket ba = basketRepository.findById(basketId).get();
			System.out.println(ba.getItems().size() + " Orders fetched");
			return ba;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@DELETE
	@Path("/delete/{basketId}")
	public void deleteByProductId(@PathParam("basketId") int basketId) {
		basketRepository.deleteById(basketId);
	}


	@POST //HTTP method
	@Path("/assign/product") //URL 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //input format
	@Produces(MediaType.APPLICATION_JSON) //output format
	@Transactional
	public Set<Product> assignProduct(@FormParam("basketId") int basketId, 
									  @FormParam("productId") int productId) {
		try {
			Basket bas = findByBasketId(basketId);
			Product prod = productService.findByProductId(productId);
			
			bas.getItems().add(prod);
			bas = registerOrUpdateBasket(bas);
			
			return bas.getItems();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//get products in basket
/*	@GET
	@Path("/products/{basketId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Product> getallProducts(@FormParam("basketId") int basketId) {
		return }*/
}