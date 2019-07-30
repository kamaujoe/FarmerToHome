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

import com.example.farmerHome.entities.Consumer;
import com.example.farmerHome.repositories.ConsumerRepository;

@Component
@Scope("singleton")
@Path("/consumer/")
public class ConsumerService {
	
	@Autowired
	private ConsumerRepository consumerRepository;

	public ConsumerService() {
		System.out.println("Consumer Service Created");
	}
	
	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_JSON})
	public Iterable<Consumer> listAllConsumers() {
		return consumerRepository.findAll();
	}
	
	@POST
	@Path("/register/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Consumer registerOrUpdateConsumer(
			@BeanParam Consumer con) {
		Consumer currentCon = findByConsno(con.getConsno());
		if(currentCon!=null) {
			currentCon.setEmail(con.getEmail());
			currentCon.setName(con.getName());
			currentCon.setAddress(con.getAddress());
			currentCon.setPhone(con.getPhone());
			con = consumerRepository.save(currentCon);
		}
		else {
			con = consumerRepository.save(con);
		}
		System.out.println("Consumer Registered "+con);
		return con;
	}

	@GET
	@Path("/find/{consno}")
	@Produces({
		MediaType.APPLICATION_JSON,
		MediaType.APPLICATION_XML
	})
	@Transactional
	public Consumer findByConsno(
			@PathParam("consno") int consno) {
		try {
			return consumerRepository.findById(consno).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@DELETE
	@Path("/delete/{consno}")
	public void deleteByConsno(
			@PathParam("consno")int consno) {
		consumerRepository.deleteById(consno);
		
		
	}

}
