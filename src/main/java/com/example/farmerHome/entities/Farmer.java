package com.example.farmerHome.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;

@Entity
@Table(name="JPA_FARMER")
@Scope("prototype")
//@Component
@XmlRootElement
@EntityListeners({FarmerLifecycleListener.class})
public class Farmer implements Serializable {
	
	
	
	@FormParam("farmerID")
	private int farmerID;
	
	@FormParam("farmerName")
	private String farmerName;
	
	@FormParam("products")
	private String products;
	
	@FormParam("farmLocation")
	private String farmLocation;
	
	
	
	
	//-> Default constructor
	public Farmer() {
		System.out.println("Farmer created");
	}

	
	
	//-> GETTERS AND SETTERS
	
	@Id
	@Column(name="farmer_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getFarmerID() {
		return farmerID;
	}

	public void setFarmerID(int farmerID) {
		this.farmerID = farmerID;
	}

	@Column(name="farmer_name", nullable=false, length=25)
	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	@Column(name="products", nullable=false, length=100)
	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	@Column(name="Farmer_location", nullable=false, length=25)
	public String getFarmLocation() {
		return farmLocation;
	}

	public void setFarmLocation(String farmLocation) {
		this.farmLocation = farmLocation;
	}

	
	
	
	//-> ToString
	@Override
	public String toString() {
		return "Farmer [farmerID=" + farmerID + ", farmerName=" + farmerName + ", products=" + products
				+ ", farmLocation=" + farmLocation + "]";
	}
	
	
	
	
	
	

}
