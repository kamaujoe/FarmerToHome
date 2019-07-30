package com.example.farmerHome.apis;


import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import com.example.farmerHome.repositories.FarmerRepository;


@Component
@Scope("singleton")
@Path("/farmers/")
public class FarmerService {
	
	
	@Autowired
	private FarmerRepository farmerRepository;
	
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
		
		Farmer currentFarmer = findByFarmerID(farmer.getFarmerID());
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

	@Path("/find/{farmerID}")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	@Transactional
	public Farmer findByFarmerID(@PathParam("farmerID") int farmerID) {
		try {
			Farmer farmer = farmerRepository.findById(farmerID).get();
			return farmer;
		} 
		catch (Exception e) {	e.printStackTrace();
		}
		return null;
	}
	
	//-> DELETE FARMER FROM DATABASE USING ID
	
	@DELETE //-> delete HTTP Method
	@Path("/delete/{farmerID}")
	public void deleteFarmerByID(@PathParam("farmerID") int farmerID) {
		
		farmerRepository.deleteById(farmerID);
	}
}
