package com.qlBanGiay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qlBanGiay.entities.Cart;
import com.qlBanGiay.entities.User;

public interface CartRepo extends JpaRepository<Cart, Integer>{
	@Query("SELECT COUNT(*) FROM Cart c WHERE c.user.id = ?1")
	public Integer countIdUser(Long idUser);
	
	public List<Cart> findByUser(User user);
}
