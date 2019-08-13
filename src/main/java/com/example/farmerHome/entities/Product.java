package com.example.farmerHome.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component // - disabled for @FormParam processing

@Scope("prototype") //one copy for each test case
@Entity
@Table(name="JPA_PRODUCT")
@EntityListeners({ProductLifecycleListener.class})
@NamedQueries({@NamedQuery(name="Product.findByPrice", query="select p from Product p where p.price between :min and :max"),
			   @NamedQuery(name="Product.findByExpiryDate", query="select p from Product p where p.expiry_date between :min and :max"),
//			   @NamedQuery(name="Product.findByCategoryId", query="select p from Product p where fk_categoryid = :fk_categoryid")}) 
			   @NamedQuery(name="Product.findByCategory", query="select p from Product p where fk_categoryid = :categoryId")}) 

@XmlRootElement
public class Product implements Serializable {
	
	private int productId;
	
	@FormParam("product_name")
	private String product_name;
	
	@FormParam("price")
	private double price;
	
	@FormParam("expiry_date")
	private int expiry_date;
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Many to Many with Basket
	private Set<Basket> items = new HashSet<>();
	
	//mapped by - check the configuration for Many to Many association in Basket class, getOrders()
	@ManyToMany(mappedBy="items")
	@XmlTransient //ignore the collections while using api
	public Set<Basket> getItems() {
		return items;
	}

	public void setItems(Set<Basket> items) {
		this.items = items;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Many to Many with Farmer
	private Set<Farmer> farmerProds = new HashSet<>();
	@ManyToMany(mappedBy="farmerProds")
	@XmlTransient
	public Set<Farmer> getFarmerProds() {
		return farmerProds;
	}

	public void setFarmerProds(Set<Farmer> farmerProds) {
		this.farmerProds = farmerProds;
	}	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Many to One with Category
	private Category currentCategory;
	

	@ManyToOne
	@JoinColumn(name="FK_CATEGORYID")
	public Category getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(Category currentCategory) {
		this.currentCategory = currentCategory;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////

	@Id //declaring the property as Primary Key
	@Column(name="product_Id") //column name
	@GeneratedValue(strategy=GenerationType.AUTO) //auto-numbering
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	@Column(name="product_name", nullable=false, length=45)
	public String getName() {
		return product_name;
	}

	public void setName(String name) {
		this.product_name = name;
	}

	@Column(name="product_price", nullable=false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name="product_expiry_date", nullable=false)
	public int getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(int expiry_date) {
		this.expiry_date = expiry_date;
	}
	
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + product_name + ", price=" + price + ", expiry_date="
				+ expiry_date + ", items=" + items + ", currentCategory=" + currentCategory
				+ "]";
	}

	//default constructor
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
}
