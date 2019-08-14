package com.example.farmerHome.entities;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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

//@Component -> disabled for @FormParam processing

@Entity
@Scope("prototype")
@Table(name = "JPA_BASKET")
@XmlRootElement
@EntityListeners({BasketLifecycleListener.class})
public class Basket implements Serializable {
	
	@FormParam("basketId")
	@Value("-1")
	private int basketId;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Many to Many relationship with Products
	private Set<Product> items = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="JPA_ITEMS", joinColumns=@JoinColumn(name="FK_BASKETID"),
			inverseJoinColumns = @JoinColumn(name = "FK_PRODUCTID"))
	//@XmlTransient //ignore the collections while using api
	public Set<Product> getItems() {
		return items;
	}
	
	public void setItems(Set<Product> items) {
		this.items = items;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//Many to One relationship with Consumers
	private Consumer currentConsumer;
	
	@ManyToOne
	@JoinColumn(name="FK_CONSUMERID")
	public Consumer getCurrentConsumer() {
		return currentConsumer;
	}
	public void setCurrentConsumer(Consumer currentConsumer) {
		this.currentConsumer = currentConsumer;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//-> Default constructor
	public Basket() {
		System.out.println("Basket Created");
	}
	
	
	//-> Getters and Setters
	@Id
	@Column(name = "Basket_Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getBasketId() {
		return basketId;
	}
	
	public void setBasketId(int basketId) {
		this.basketId = basketId;
	}
	
	
	//-> ToString
	@Override
	public String toString() {
		return "Basket [basketId=" + basketId + "]";
	}
}
