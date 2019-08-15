package com.example.farmerHome.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

//package com.example.farmerHome.entities;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//import javax.ws.rs.FormParam;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
//
//import org.springframework.context.annotation.Scope;
//
//@Scope("prototype")
//@Entity
//@Table(name = "JPA_ORDERS")
//@NamedQueries({@NamedQuery(name="Order.findByBasketId", query="select o from Order o where basket_id = :basket_id"),
//				@NamedQuery(name="Order.findByProductId", query="select p from Order p where product_id = :product_id and basket_id = :basket_id"),
//				@NamedQuery(name="Order.getOrderId", query="select orderId from Order where product_id = :product_id and basket_id = :basket_id")})
//@XmlRootElement
//public class Order implements Serializable {
//	
//	@FormParam("orderId")
//	private int orderId;
//	
//	@FormParam("basketId")
//	private int basketId;
//	
//	@FormParam("productId")
//	private int productId;
//	
//	@FormParam("quantity")
//	private int quantity;
//	
//	public Order() {
//		System.out.println("Order Created");
//	}
//
////////////////////////////////////////////////////////////////////////////
//	
//	
//	@Id //declare the property as Primary Key
//	@Column(name = "Order_Id")
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	public int getOrderId() {
//		return orderId;
//	}	
//	
//	public void setOrderId(int orderId) {
//		this.orderId = orderId;
//	}
//	
//	
//	@Column(name="Product_Id")
//	public int getProductId() {
//		return productId;
//	}
//
//	public void setProductId(int productId) {
//		this.productId = productId;
//	}
//	
//	
//	@Column(name="Quantity")
//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//	
//	@Column(name="Basket_Id", nullable=false, length=15)
//	public int getBasketId() {
//		return basketId;
//	}
//
//	public void setBasketId(int basketId) {
//		this.basketId = basketId;
//	}
//	
//	@Override
//	public String toString() {
//		return "Order [orderId=" + orderId + 
//				" ,productId=" + productId + " ,quantity=" 
//				+ quantity + ",basketId=" + basketId + "]";
//	}
//
//
//}
@Scope("prototype")
@Entity
@Table(name = "JPA_ORDERS")
@NamedQueries({@NamedQuery(name="Order.findByProductId", query="select p from Order p where product_id = :product_id and basket_id = :basket_id")
		,@NamedQuery(name="Order.getOrderId", query="select id from Order where product_id = :product_id and basket_id = :basket_id")
	,@NamedQuery(name="Order.getOrderByBasket", query="select a from Order a where basket_id = :basket_id")
})
@XmlRootElement
public class Order implements Serializable{
	
	private int id;
	private Product product;
	private Basket basket;
	
	private int quantity;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	public int getId() {
		return id;		
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "basket_id")
	@XmlTransient
	public Basket getBasket() {
		return basket;
	}
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	//@XmlTransient
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	public Order() {
		System.out.println("Order Created");
	}
	
	
}


