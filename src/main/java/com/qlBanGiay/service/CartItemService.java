package com.qlBanGiay.service;

import java.util.List;



import com.qlBanGiay.entities.CartItem;

public interface CartItemService {
	public List<CartItem> findAll();
	public Boolean add(CartItem cartItem);
	public CartItem update(CartItem cartItem);
	public CartItem finById(Integer id);
	public Boolean delete(Integer id);
	public Boolean deleteAll();
	
	public CartItem checkCartItem(Integer cartId, Integer productId);
	public void deleteByCartId(Integer cartId);
}
