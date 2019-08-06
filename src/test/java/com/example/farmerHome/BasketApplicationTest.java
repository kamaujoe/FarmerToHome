package com.example.farmerHome;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.farmerHome.apis.BasketService;
import com.example.farmerHome.apis.ConsumerService;
import com.example.farmerHome.entities.Basket;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BasketApplicationTest {
	
	@Autowired
	BasketService basketService;
	
	@Autowired
	ConsumerService consumerService;
 
	@Test
	public void exampleBasketTest() {
		System.out.println("Basket test case scenarios");
	}
	

	@Test
	public void addBasketUsingService() {
		Basket basket = new Basket();
		basket = basketService.registerOrUpdateBasket(basket);
		assertNotNull(basket);
	}
	
/*	// CRUD OPERATIONS - Find basket by id
	@Test
	public void findByBasketIdUsingService() {
		int basketId = 6;
		assertNotNull(basketService.findByBasketId(basketId));
	}
	
	// CRUD OPERATIONS - Delete basket by id
	@Test
	public void deleteByBasketIdUsingService() {
		int basketId = 9;
		basketService.deleteByProductId(basketId);
		assertNull(basketService.findByBasketId(basketId));
	}*/

}