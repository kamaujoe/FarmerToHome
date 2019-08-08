package com.example.farmerHome.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.farmerHome.entities.Basket;
import com.example.farmerHome.entities.Product;

import javassist.compiler.ast.Variable;

@Component
public interface BasketRepository
	extends CrudRepository<Basket, Integer>{
	

	

}
