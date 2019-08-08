package com.example.farmerHome.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.farmerHome.entities.Product;
import com.example.farmerHome.entities.ProductCategories;

@Component
public interface ProductRepository extends CrudRepository<Product, Integer>{

	//Select/filter queries
	public List<Product> findByCategoryId(Integer fk_categoryid);
	
	public List<Product> findByPrice(Double min, Double max);
	
	public List<Product> findByExpiryDate(Integer min, Integer max);
	
	
}
