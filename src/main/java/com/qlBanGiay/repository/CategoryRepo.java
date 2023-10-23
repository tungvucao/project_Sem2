package com.qlBanGiay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qlBanGiay.entities.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{
	@Query("SELECT c FROM Category c WHERE c.cateName LIKE %?1%")
	public List<Category> findCateByName(String keyword);
}
