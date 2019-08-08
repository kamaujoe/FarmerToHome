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
			   @NamedQuery(name="Product.findByCategoryId", query="select p from Product p where fk_categoryid = :fk_categoryid")}) 
@XmlRootElement
public class Product implements Serializable {
	
	private int productId;
	
	@FormParam("product_name")
	private String name;
	
	@FormParam("price")
	private double price;
	
	@FormParam("expiry_date")
	private int expiry_date;
	
	@FormParam("size")
	ProductSizes size;
	
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
	private Set<Farmer> suppliers = new HashSet<>();
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="JPA_SUPPLIERS",
		joinColumns=@JoinColumn(name="FK_PRODUCTID"),
		inverseJoinColumns=@JoinColumn(name="FK_FARMERID"))
	@XmlTransient //ignore the collections while using api
	public Set<Farmer> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<Farmer> suppliers) {
		this.suppliers = suppliers;
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
	
	@Column(name="product_name", nullable=false, length=15)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@Column(name="product_size") //not nullable because some products won't have size
	@Enumerated(EnumType.STRING)
	public ProductSizes getSize() {
		return size;
	}

	public void setSize(ProductSizes size) {
		this.size = size;
	}

	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", expiry_date="
				+ expiry_date + ", size=" + size + ", items=" + items + ", suppliers=" + suppliers
				+ ", currentCategory=" + currentCategory + "]";
	}

	//default constructor
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	
/*	//TO DO - FETCH BY DISCOUNT FEATURE
	public double productDiscount() {
		double discount;
		if (expiry_date >= 9) {
			discount = price * 0.5;
			return discount;
		} else if (expiry_date == 8) {
			discount = price * 0.25;
			return discount;
		} else {
			return price;
		}
	}
	
	//TO DO - SEARCH BUTTON FEATURE
    public List<Product> getSearchProducts() {

        List<Product> products;

        if(null == keyword || "".equals(keyword)) {
            products = new ArrayList<Product>();
        } else {
            products = productRepository.getInstance()
                .searchByProductName(keyword);
        }

        List<ProductBean> productBeans = new ArrayList<ProductBean>();

        for(Product product: products) {
            ProductBean productBean = new ProductBean();
            productBean.setProduct(product);
            productBeans.add(productBean);
        }
        return productBeans;
    }*/
}
