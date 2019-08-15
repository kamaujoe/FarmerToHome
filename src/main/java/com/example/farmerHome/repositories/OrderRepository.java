package com.example.farmerHome.repositories;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


import com.example.farmerHome.entities.Order;
import com.example.farmerHome.entities.Product;



@Component
public interface OrderRepository
	extends CrudRepository<Order, Integer>{
	
	


	public Order findByProductId(Integer product_id, Integer basket_id);

	public Integer getOrderId(Integer productId, Integer basketId);
	
	public List<Order> getOrderByBasket(Integer basket_id);
	
}