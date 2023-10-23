package com.qlBanGiay.entities;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
	@Id
	@Column(name = "productId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "proName", columnDefinition = "nvarchar")
	private String proName;
	@Column(name = "price")
	private Double price;
	@Column(name = "salePrice")
	private Double salePrice;
	@Column(name = "image")
	private String image;
	@Column(name = "publish")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDateTime publish;
	@Column(name = "description", columnDefinition = "nvarchar(1000)" )
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "categoryId",referencedColumnName = "cateId")
	private Category category;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(Integer id, String proName, Double price, Double salePrice, String image, LocalDateTime publish,
			String description, Category category) {
		super();
		this.id = id;
		this.proName = proName;
		this.price = price;
		this.salePrice = salePrice;
		this.image = image;
		this.publish = publish;
		this.description = description;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDateTime getPublish() {
		return publish;
	}

	public void setPublish(LocalDateTime publish) {
		this.publish = publish;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
}
