package com.qlBanGiay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlBanGiay.entities.CartItem;
import com.qlBanGiay.repository.CartItemRepo;
@Service
public class CartItemServiceImple implements CartItemService{

	@Autowired
	private CartItemRepo cartItemRepo;
	
	@Override
	public List<CartItem> findAll() {
		// TODO Auto-generated method stub
		return this.cartItemRepo.findAll();
	}
	
	@Override
	public Boolean add(CartItem cartItem) {
		try {
			this.cartItemRepo.save(cartItem);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public CartItem update(CartItem cartItem) {
		// TODO Auto-generated method stub
		return this.cartItemRepo.save(cartItem);
	}

	@Override
	public CartItem finById(Integer id) {
		return this.cartItemRepo.findById(id).get();
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			this.cartItemRepo.delete(finById(id));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Boolean deleteAll() {
		try {
			this.cartItemRepo.deleteAll();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public CartItem checkCartItem(Integer cartId, Integer productId) {
		// TODO Auto-generated method stub
		return this.cartItemRepo.findByCartIdAndProductId(cartId,productId);
	}
	
	@Override
	public void deleteByCartId(Integer cartId) {
		// TODO Auto-generated method stub
		this.cartItemRepo.deleteByCartId(cartId);
	}
	
}
