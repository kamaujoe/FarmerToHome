package com.example.farmerHome.apis;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import com.example.farmerHome.entities.Product;
import com.example.farmerHome.entities.ProductCategories;
import com.example.farmerHome.repositories.ProductRepository;

@Component //indicate to Spring to create an object of this class as a component
@Scope("singleton") //creates one object per application - Default option
@Path("/products/") //map the URL pattern with the class as service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	
	public ProductService() {
		System.out.println("Product service created");
	}
	
	@POST //HTTP method to send the form data
	@Path("/register") //URL pattern 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //form data
	@Produces(MediaType.APPLICATION_JSON) //JSON data
	@Transactional //to help fetching dependent data
	public Product registerOrUpdateProduct(@BeanParam Product prod) {
		Product currentProd = findByProductId(prod.getProductId());
		
		if (currentProd != null) { //update the existing product with form values
			currentProd.setName(prod.getName());
			currentProd.setCategory(prod.getCategory());
			currentProd.setExpiry_date(prod.getExpiry_date());
			currentProd.setPrice(prod.getPrice());
			currentProd.setQuantity(prod.getQuantity());
			currentProd.setSize(prod.getSize());
			//save changes in repository
			prod = productRepository.save(currentProd);
		
		} else { //create new product
			prod = productRepository.save(prod);
		}
		System.out.println("Product registered " + prod);
		return prod;
	}
	
	@GET
	@Path("/find/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional 
	public Product findByProductId(@PathParam("productId") int productId) {
		//fetches product details from DB by productId
		//@PathParam - argument for the method
		try {
		Product prod = productRepository.findById(productId).get();
		System.out.println(prod);
			return prod;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/allProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Product> getAllProducts() {
		Iterable<Product> products = productRepository.findAll();
		System.out.println(products);
		return products;
	}
	
	@GET
	@Path("/fetchByCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> fetchProductsByCategory(
			@QueryParam("category") ProductCategories category) {
		return productRepository.findByCategory(category);
	}

	@GET
	@Path("/fetchByPrice")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> fetchProductsByPriceRange(
			@QueryParam("min") double min, 
			@QueryParam("max") double max) {
		//Commented out as it was successfully tested with db
		//List<Product> prods = productRepository.findByPrice(min, max);
		//System.out.println(prods);
		//return prods;
		return productRepository.findByPrice(min, max);
	}	
	
	@GET
	@Path("/fetchByExpiryDate")
	@Produces(MediaType.APPLICATION_JSON)	
	public List<Product> fetchProductsByExpiryDate(
			@QueryParam("min") int min, 
			@QueryParam("max") int max) {
		//List<Product> prods = productRepository.findByExpiryDate(min, max);
		//System.out.println(prods);
		//return prods;
		return productRepository.findByExpiryDate(min, max);
	}
	
	@DELETE
	@Path("/delete/{productId}")
	public void deleteByProductId(@PathParam("productId") int productId) {
		productRepository.deleteById(productId);
	}
}
