package com.qlBanGiay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlBanGiay.entities.Cart;
import com.qlBanGiay.entities.CartItem;
import com.qlBanGiay.entities.User;
import com.qlBanGiay.repository.CartRepo;
@Service
public class CartServiceImple implements CartService{

	@Autowired
	private CartRepo cartRepo;
	
	@Override
	public Boolean addCart(Cart cart) {
		try {
			this.cartRepo.save(cart);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Integer check(Long idUser) {
		// TODO Auto-generated method stub
		return this.cartRepo.countIdUser(idUser);
	}
	
	@Override
	public Cart findByUser(User user) {
		// TODO Auto-generated method stub
		return this.cartRepo.findByUser(user).get(0);
	}
	
	@Override
	public List<CartItem> findByCartId(Integer cartId) {
		// TODO Auto-generated method stub
		return null;
	}

}
