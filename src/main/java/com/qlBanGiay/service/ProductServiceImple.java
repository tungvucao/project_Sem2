package com.qlBanGiay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.qlBanGiay.entities.Product;
import com.qlBanGiay.repository.ProductRepo;

@Service
public class ProductServiceImple implements ProductService{

	@Autowired
	private ProductRepo productRepo;
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return this.productRepo.findAll();
	}

	@Override
	public Boolean createOrUpdate(Product product) {
		try {
			this.productRepo.save(product);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Product findById(Integer id) {
		// TODO Auto-generated method stub
		return this.productRepo.findById(id).get();
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			this.productRepo.delete(findById(id));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> findProByName(String keyword) {
		// TODO Auto-generated method stub
		return this.productRepo.findProByName(keyword);
	}

	@Override
	public Page<Product> getPage(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo-1, 10);
		return this.productRepo.findAll(pageable);
	}

	@Override
	public Page<Product> findProByName(String keyword, Integer pageNo) {
		List<Product> listPro = this.findProByName(keyword);
		Pageable pageable = PageRequest.of(pageNo-1, 10);
		
		Integer start = (int) pageable.getOffset();
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > listPro.size() ? listPro.size() : pageable.getOffset() + pageable.getPageSize());
		
		listPro = listPro.subList(start, end);
		return new PageImpl<Product>(listPro, pageable, this.findProByName(keyword).size());
	}

	//Get New Product
	@Override
	public List<Product> findNewProduct() {
		// TODO Auto-generated method stub
		return this.productRepo.findNewProduct();
	}

	//Get All Product
	@Override
	public Page<Product> getPageUser(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo-1, 12);
		return this.productRepo.findAll(pageable);
	}
	
	@Override
	public Page<Product> findProByNameUser(String keyword, Integer pageNo) {
		List<Product> listPro = this.findProByName(keyword);
		Pageable pageable = PageRequest.of(pageNo-1,12);
		
		Integer start = (int) pageable.getOffset();
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > listPro.size() ? listPro.size() : pageable.getOffset() + pageable.getPageSize());
		
		listPro = listPro.subList(start, end);
		return new PageImpl<Product>(listPro, pageable, this.findProByName(keyword).size());
	}
	
	//Sale Product
	@Override
	public List<Product> findProSale50() {
		// TODO Auto-generated method stub
		return this.productRepo.findProSale50();
	}
	
	@Override
	public Page<Product> findAllProSale50(Pageable pageNo) {
		// TODO Auto-generated method stub
		return this.productRepo.findAllProSale50(pageNo);
	}

	@Override
	public Page<Product> getPageUserSale(Integer pageNo,Sort sort) {
		Pageable pageable = PageRequest.of(pageNo-1, 12,sort);
		return this.productRepo.findAllProSale50(pageable);
	}

	@Override
	public List<Product> findProSale50ByName(String keyword) {
		// TODO Auto-generated method stub
		return this.productRepo.findProSale50ByName(keyword);
	}

	@Override
	public Page<Product> findProSale50ByName(String keyword, Integer pageNo,Sort sort) {
		List<Product> listPro = this.findProSale50ByName(keyword);
		Pageable pageable = PageRequest.of(pageNo-1, 12,sort);
		
		Integer start = (int) pageable.getOffset();
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > listPro.size() ? listPro.size() : pageable.getOffset() + pageable.getPageSize());
		
		listPro = listPro.subList(start, end);
		return new PageImpl<Product>(listPro, pageable, this.findProSale50ByName(keyword).size());
	}
	
	// Get Product By CateId
	@Override
	public List<Product> findProByCateId(Integer cateId, Integer id) {
		// TODO Auto-generated method stub
		return this.productRepo.findProByCateId(cateId, id);
	}
	
	@Override
	public Page<Product> findAllProByCateId(Integer cateId, Pageable pageNo) {
		// TODO Auto-generated method stub
		return this.productRepo.findAllProByCateId(cateId,pageNo);
	}

	@Override
	public Page<Product> getPageUserAllPro(Integer cateId,Integer pageNo, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo-1, 12,sort);
		return this.productRepo.findAllProByCateId(cateId,pageable);
	}

	@Override
	public List<Product> findProByNameAndCateId(Integer cateId, String keyword) {
		// TODO Auto-generated method stub
		return this.productRepo.findProByNameAndCateId(cateId, keyword);
	}

	@Override
	public Page<Product> findProByNameAndCateId(Integer cateId, String keyword, Integer pageNo, Sort sort) {
		List<Product> listPro = this.findProByNameAndCateId(cateId, keyword);
		Pageable pageable = PageRequest.of(pageNo-1, 12,sort);
		
		Integer start = (int) pageable.getOffset();
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > listPro.size() ? listPro.size() : pageable.getOffset() + pageable.getPageSize());
		
		listPro = listPro.subList(start, end);
		return new PageImpl<Product>(listPro, pageable, this.findProByNameAndCateId(cateId,keyword).size());
	}
	
	//Get All Product - CateStatus True
	@Override
	public Page<Product> allProHaveCateTrue(Pageable pageNo) {
		// TODO Auto-generated method stub
		return this.productRepo.allProHaveCateTrue(pageNo);
	}
	
	@Override
	public List<Product> findProTrueByName(String keyword) {
		// TODO Auto-generated method stub
		return this.productRepo.findProTrueByName(keyword);
	}
	
	@Override
	public Page<Product> getPageProTrueUser(Integer pageNo, Sort sort) {
		Pageable pageable = PageRequest.of(pageNo-1, 12,sort);
		return this.productRepo.allProHaveCateTrue(pageable);
	}
	
	@Override
	public Page<Product> findProTrueByName(String keyword, Integer pageNo,Sort sort) {
		List<Product> listPro = this.findProTrueByName(keyword);
		Pageable pageable = PageRequest.of(pageNo-1, 12,sort);
		
		Integer start = (int) pageable.getOffset();
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > listPro.size() ? listPro.size() : pageable.getOffset() + pageable.getPageSize());
		
		listPro = listPro.subList(start, end);
		return new PageImpl<Product>(listPro, pageable, this.findProTrueByName(keyword).size());
	}
	



	
}