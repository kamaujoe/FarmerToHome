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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component -> disabled for form parameter processing

@Scope("prototype")
@Entity
@Table(name = "JPA_BASKET")
@EntityListeners({BasketLifecycleListener.class})
@XmlRootElement
public class Basket implements Serializable {
<<<<<<< HEAD

	private int Faith;

	private int gregchappell;

	private int MonicaAbreu;
	
	private int milesnsmith97;

	private int joseph;

=======
>>>>>>> branch 'master' of https://github.com/kamaujoe/FarmerToHome.git
	
	@FormParam("basketId")
	@Value("-1")
	private int basketId;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// Many to Many relationship with products
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
	
	
	// Many to One relationship with Consumers
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
	
	
	public Basket() {
		System.out.println("Basket Created");
	}


	
	
	@Id //declare the property as Primary Key
	@Column(name = "Basket_Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getBasketId() {
		return basketId;
	}
	
	
	public void setBasketId(int basketId) {
		this.basketId = basketId;
	}
	@Override
	public String toString() {
		return "Basket [basketId=" + basketId + "]";
	}

	
	
	
}
