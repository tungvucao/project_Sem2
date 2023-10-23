package com.qlBanGiay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.qlBanGiay.entities.CartItem;

import jakarta.transaction.Transactional;
@Transactional
public interface CartItemRepo extends JpaRepository<CartItem, Integer>{
	
	CartItem findByCartIdAndProductId(Integer cartId, Integer productId);
	@Modifying
	@Query("delete from CartItem c where c.cart.id = ?1")
	public void deleteByCartId(Integer cartId);
}
