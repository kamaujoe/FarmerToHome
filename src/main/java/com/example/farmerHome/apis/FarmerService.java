package com.example.farmerHome.apis;


import java.util.Set;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.farmerHome.entities.Farmer;
import com.example.farmerHome.entities.Product;
import com.example.farmerHome.repositories.FarmerRepository;


@Component
@Scope("singleton")
@Path("/farmers/")
public class FarmerService {
	
	
	@Autowired
	private FarmerRepository farmerRepository;
	
	@Autowired
	private ProductService productService;
	
	public FarmerService() {
		System.out.println("Farmer service created");
	}

	
	//-> ADD FARMER TO DATABASE
	
	@POST //-> HTTP Meth0d to send the form Data
	@Path("/register/") //-> URL Pattern
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //-> Form Data
	@Produces(MediaType.APPLICATION_JSON) //-> JSON Data
	@Transactional
	public Farmer registerOrUpdateFarmer(@BeanParam Farmer farmer) {
		
		//-> this code takes in user input and if the data exist in the database, 
			// it changes the current data, if not it saves it as new farmer data
		
		Farmer currentFarmer = findByFarmerId(farmer.getFarmerId());
		if(currentFarmer!=null) {
			
			currentFarmer.setFarmerName(farmer.getFarmerName());
			currentFarmer.setFarmLocation(farmer.getFarmLocation());
			farmer = farmerRepository.save(currentFarmer);
		}
		else {
			farmer = farmerRepository.save(farmer);
		}
		System.out.println("Farmer Registered"+farmer);
		return farmer;
		
		
		//farmer = farmerRepository.save(farmer);
		//System.out.println("Farmer Registered "+farmer);
		//return farmer;
	}

	
	//-> FIND FARMER IN DATABASE

	@Path("/find/{farmerId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Farmer findByFarmerId(@PathParam("farmerId") int farmerId) {
		try {
			Farmer farmer = farmerRepository.findById(farmerId).get();
			return farmer;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//-> DELETE FARMER FROM DATABASE USING ID
	
	@DELETE //-> delete HTTP Method
	@Path("/delete/{farmerId}")
	public void deleteFarmerById(@PathParam("farmerId") int farmerId) {
		
		farmerRepository.deleteById(farmerId);
	}
	
	//Ask Sameer about postman error 
	//failed to lazily initialize a collection of role: com.example.farmerHome.entities.Farmer.farmerProds, could not initialize proxy - no Session
	//product is assigned in db but gives that error in postman
	@POST //HTTP method
	@Path("/assign/product") //URL 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) //input format
	@Produces(MediaType.APPLICATION_JSON) //output format
	@Transactional
	public Set<Product> assignProduct(@FormParam("productId") int productId, 
									  @FormParam("farmerId") int farmerId) {
		try {
			Farmer far = findByFarmerId(farmerId);
			Product prod = productService.findByProductId(productId);
			
			prod.getSuppliers().add(far);
			prod = productService.registerOrUpdateProduct(prod);
	
			return far.getFarmerProds();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
