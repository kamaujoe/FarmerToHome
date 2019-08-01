package com.example.farmerHome;

import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.farmerHome.apis.BasketService;
import com.example.farmerHome.apis.ConsumerService;
import com.example.farmerHome.apis.FarmerService;
import com.example.farmerHome.apis.ProductService;
import com.example.farmerHome.entities.Basket;
import com.example.farmerHome.entities.Consumer;
import com.example.farmerHome.entities.Farmer;
import com.example.farmerHome.entities.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FarmerHomeApplicationTests {

	@Autowired
	BasketService basketService;
	
	@Autowired
	ConsumerService consumerService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	FarmerService farmerService;
	
	
	//MANAGE ASSOCIATIONS
	//One to Many [Consumer - Basket]
	@Test
	public void assignConsumerToBasket(){
		int basketId = 6;
		int consno = 4;
		Basket basket = consumerService.assignBasket(consno, basketId);
		assertNotNull(basket.getCurrentConsumer());
	}
	
	
	//Many to Many [Product - Basket]
	@Test
	public void assignProductToBasket() {
		int basketId = 6;
		int productId = 13;
		Set<Product> prods = basketService.assignProduct(basketId, productId);
		assertNotNull(prods);		
	}
	
	
	//Many to Many [Farmer - Product]
	@Test
	public void assignFarmerToProduct() {
		int productId = 3;
		int farmerId = 5;
		Set<Product> products = farmerService.assignProduct(productId, farmerId);	
		assertNotNull(products);
	}
	


}
