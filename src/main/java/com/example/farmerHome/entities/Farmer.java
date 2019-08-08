package com.example.farmerHome.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.context.annotation.Scope;

//@Component

@Entity
@Table(name="JPA_FARMER")
@Scope("prototype")
@XmlRootElement
@EntityListeners({FarmerLifecycleListener.class})
public class Farmer implements Serializable {
	
	@FormParam("farmerId")
	private int farmerId;
	
	@FormParam("farmerName")
	private String farmerName;
	
	@FormParam("products")
	private String products;
	
	@FormParam("farmLocation")
	private String farmLocation;
	
	
	//Many to Many with Products
	private Set<Product> farmerProds = new HashSet<>();
	
	@ManyToMany(mappedBy="suppliers")
	@XmlTransient
	public Set<Product> getFarmerProds() {
		return farmerProds;
	}
	
	public void setFarmerProds(Set<Product> farmerProds) {
		this.farmerProds = farmerProds;
	}
	
	
	//-> Default constructor
	public Farmer() {
		System.out.println("Farmer created");
	}


	//-> GETTERS AND SETTERS
	@Id
	@Column(name="farmer_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
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
		return "Farmer [farmerId=" + farmerId + ", farmerName=" + farmerName + ", products=" + products
				+ ", farmLocation=" + farmLocation + "]";
	}
	
	
	
	
	
	

}
