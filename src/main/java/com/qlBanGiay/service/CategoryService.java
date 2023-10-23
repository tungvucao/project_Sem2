package com.qlBanGiay.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.qlBanGiay.entities.Category;

public interface CategoryService {
	public List<Category> findAll();
	public Boolean create(Category category);
	public Boolean update(Category category);
	public Category findById(Integer id);
	public Boolean delete(Integer id);
	public List<Category> findCateByName(String keyword);
	
	public Page<Category> getPage(Integer pageNo);
	public Page<Category> findCateByName(String keyword,Integer pageNo);
}
