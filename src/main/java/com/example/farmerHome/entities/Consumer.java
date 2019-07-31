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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component

@Scope("prototype")
@Entity
@Table(name="JPA_CONSUMER")
@XmlRootElement
@EntityListeners({ConsumerLifecycleListener.class})
public class Consumer implements Serializable {

	@FormParam("consno")
	@Value("-1")
	private int consno;
	
	@FormParam("name")
	@Value("Default Consumer")
	private String name;
	
	@FormParam("email")
	@Value("Default@gmail.com")
	private String email;
	
	@FormParam("address")
	@Value("1 Default Lane")
	private String address;
	
	@FormParam("phone")
	@Value("12345")
	private long phone;
	
	//One to Many - One Consumer -> Many Baskets
	private Set<Basket> orderHistory = new HashSet<>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="currentConsumer")
	@XmlTransient //ignore the collections while using api
	public Set<Basket> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(Set<Basket> orderHistory) {
		this.orderHistory = orderHistory;
	}
	
	
	public Consumer() {
		System.out.println("Consumer Created");
	}
	


	@Id
	@Column(name="consumer_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getConsno() {
		return consno;
	}
	public void setConsno(int consno) {
		this.consno = consno;
	}
	
	@Column(name="consumer_name",nullable=false,length=45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Consumer [consno=" + consno 
				+ ", name=" + name 
				+ ", email=" + email 
				+ ", address=" + address
				+ ", phone=" + phone + "]";
	}
	
	
}
