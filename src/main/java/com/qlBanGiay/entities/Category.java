package com.qlBanGiay.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Category")
public class Category {
	@Id
	@Column(name = "cateId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "cateName", columnDefinition = "nvarchar")
	private String cateName;
	@Column(name = "cateStatus")
	private Boolean cateStatus;

	@OneToMany(mappedBy = "category")
	private Set<Product> products;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(Integer id, String cateName, Boolean cateStatus, Set<Product> products) {
		super();
		this.id = id;
		this.cateName = cateName;
		this.cateStatus = cateStatus;
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Boolean getCateStatus() {
		return cateStatus;
	}

	public void setCateStatus(Boolean cateStatus) {
		this.cateStatus = cateStatus;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	
}
