package com.example.farmerHome.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.farmerHome.entities.Farmer;

@Component
public interface FarmerRepository extends CrudRepository<Farmer, Integer> {
	
	public List<Farmer> findByEmailAndPass(String email, String farmerPassword);
}
