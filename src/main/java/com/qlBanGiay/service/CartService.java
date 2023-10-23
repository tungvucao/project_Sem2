package com.qlBanGiay.service;

import java.util.List;

import com.qlBanGiay.entities.Cart;
import com.qlBanGiay.entities.CartItem;
import com.qlBanGiay.entities.User;

public interface CartService {
	public Boolean addCart(Cart cart);
	public Integer check(Long idUser);
	
	public Cart findByUser(User user);
	

	public List<CartItem> findByCartId(Integer cartId);
}
