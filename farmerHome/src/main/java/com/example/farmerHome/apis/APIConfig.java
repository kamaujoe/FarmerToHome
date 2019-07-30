package com.example.farmerHome.apis;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;


//create the Jersey server configuration class
@Component
public class APIConfig extends ResourceConfig {
	public APIConfig() {
		//register each service class in ResourceConfig
		register(ProductService.class);
		register(FarmerService.class);
	}
}

