package com.example.farmerHome;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.farmerHome.apis.FarmerService;
import com.example.farmerHome.entities.Farmer;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FarmerApplicationTest {

	
	@Autowired
	FarmerService farmerService;
	
	
/*	@Test
	public void contextLoads() {
		System.out.println("system test executed");
	}
*/
	
	//-> ADD NEW FARMER TO DATABASE
	@Test
	public void addFarmerUsingService() {
		//farmer.setFarmerID(1001);
		Farmer farmer = new Farmer();
		farmer.setFarmerName("Faith's Fresh Fruits");
		farmer.setFarmLocation("NEWCASTLE");
		farmer.setProducts("freshest fruit around");
		farmer = farmerService.registerOrUpdateFarmer(farmer);
		assertNotNull(farmer);
	}
	
	
/*	//-> FIND FARMER IN DATABASE
	@Test
	public void findFarmerUsingService() {
		int farmerId = 6;
		assertNotNull(farmerService.findByFarmerId(farmerId));
	}
	
	
	//-> DELETE FARMER FROM DATABASE
	@Test
	public void deleteFarmerUsingService() {
		
		int farmerId = 10;
		farmerService.deleteFarmerById(farmerId);
		assertNull(farmerService.findByFarmerId(farmerId));
	}*/
}
