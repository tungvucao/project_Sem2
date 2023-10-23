package com.qlBanGiay.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.qlBanGiay.entities.Product;

public interface ProductService {
	public List<Product> findAll();
	public Boolean createOrUpdate(Product product);
	public Product findById(Integer id);
	public Boolean delete(Integer id);
	public List<Product> findProByName(String keyword);
	
	//Find Admin
	public Page<Product> getPage(Integer pageNo);
	public Page<Product> findProByName(String keyword,Integer pageNo);
	//Find User
	public Page<Product> getPageUser(Integer pageNo);
	public Page<Product> findProByNameUser(String keyword,Integer pageNo);
	
	//Sale Product
	public List<Product> findProSale50();
	public Page<Product> getPageUserSale(Integer pageNo,Sort sort);
	public Page<Product> findAllProSale50(Pageable pageNo);
	public List<Product> findProSale50ByName(String keyword);
	public Page<Product> findProSale50ByName(String keyword,Integer pageNo,Sort sort);
	
	//New Product
	public List<Product> findNewProduct();
	
	//Get Product By CateId
	public List<Product> findProByCateId(Integer cateId,Integer id);
	public Page<Product> getPageUserAllPro(Integer cateId,Integer pageNo,Sort sort);
	public Page<Product> findAllProByCateId(Integer cateId, Pageable pageNo);
	public List<Product> findProByNameAndCateId(Integer cateId,String keyword);
	public Page<Product> findProByNameAndCateId(Integer cateId,String keyword, Integer pageNo, Sort sort);
	
	//Get All Product Have CateStatus True
	public Page<Product> allProHaveCateTrue(Pageable pageNo);
	public Page<Product> getPageProTrueUser(Integer pageNo,Sort sort);
	public List<Product> findProTrueByName(String keyword);
	public Page<Product> findProTrueByName(String keyword, Integer pageNo,Sort sort);
	
	
}
