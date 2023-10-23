package com.qlBanGiay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qlBanGiay.entities.Category;
import com.qlBanGiay.repository.CategoryRepo;

@Service
public class CategoryServiceImple implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public List<Category> findAll() {
		return this.categoryRepo.findAll();
	}

	@Override
	public Boolean create(Category category) {
		try {
			this.categoryRepo.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean update(Category category) {
		try {
			this.categoryRepo.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Category findById(Integer id) {
		// TODO Auto-generated method stub
		return this.categoryRepo.findById(id).get();
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			this.categoryRepo.delete(this.findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Category> findCateByName(String keyword) {
		// TODO Auto-generated method stub
		return this.categoryRepo.findCateByName(keyword);
	}
	
	@Override
	public Page<Category> getPage(Integer pageNo) {
		Pageable pageable = PageRequest.of(pageNo-1, 10);
		return this.categoryRepo.findAll(pageable);
	}

	@Override
	public Page<Category> findCateByName(String keyword, Integer pageNo) {
		List<Category> listCate = this.findCateByName(keyword);
		Pageable pageable = PageRequest.of(pageNo-1, 10);
		
		Integer start = (int) pageable.getOffset();
		Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > listCate.size() ? listCate.size() : pageable.getOffset() + pageable.getPageSize());
		
		listCate = listCate.subList(start, end);
		return new PageImpl<Category>(listCate, pageable, this.findCateByName(keyword).size());
		
	}

}
