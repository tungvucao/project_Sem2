package com.qlBanGiay.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qlBanGiay.entities.CartItem;
import com.qlBanGiay.service.CartItemService;

@RestController
@RequestMapping("/cartItemApi")
public class CartItemApiController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@GetMapping
	public List<CartItem> getAll(){
		return this.cartItemService.findAll();
	}
	
	@PutMapping("/{id}")
	public CartItem update(@PathVariable("id") Integer id, @RequestBody CartItem cartItemNew) {
		CartItem cartItem = this.cartItemService.finById(id);
		cartItem.setQuantity(cartItemNew.getQuantity());
		return this.cartItemService.update(cartItem);
	}
}
