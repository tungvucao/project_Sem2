package com.qlBanGiay.controllers.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qlBanGiay.entities.Cart;
import com.qlBanGiay.entities.CartItem;
import com.qlBanGiay.entities.Category;
import com.qlBanGiay.entities.CustomUserDetails;
import com.qlBanGiay.service.CartItemService;
import com.qlBanGiay.service.CartService;
import com.qlBanGiay.service.CategoryService;
import com.qlBanGiay.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartItemService cartItemService ;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public String listCart( Principal principal,Model model) {
		if (principal == null) {
			return "redirect:/login";
		}
		CustomUserDetails user =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = this.cartService.findByUser(user.getUser());
		model.addAttribute("listCart", cart);		
		Integer idcart = cart.getId();
		model.addAttribute("idcart", idcart);
		model.addAttribute("totalPrice", cart.totalPrice());
		
		List<Category> allCategory = this.categoryService.findAll();
		List<Category> allCategoryTrue = new ArrayList<Category>();
		for (Category category : allCategory) {
			if (category.getCateStatus() == true) {
				allCategoryTrue.add(category);
			}
		}
		model.addAttribute("allCategoryTrue", allCategoryTrue);
		
		return "user/cart";
	}
	
	@PostMapping
	public String addCart(@RequestParam("proId") Integer proId, @RequestParam("quantity") Integer quantity, Principal principal) {
		
		if (principal == null) {
			return "redirect:/login";
		}
		Cart cart = new Cart();
		CartItem cartItem = new CartItem();
		CustomUserDetails user =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//Kiểm tra userId exsit
		cart.setUser(user.getUser());
		if (this.cartService.check(user.getUser().getId()) == 0) {
			this.cartService.addCart(cart);			
			cartItem.setCart(cart);
		}else {
			Cart cart1 = this.cartService.findByUser(user.getUser());
			cartItem.setCart(cart1);
		}
		
		//Kiểm tra Product exsit
		CartItem checkCartItem = this.cartItemService.checkCartItem(cartItem.getCart().getId(), proId);
		if (checkCartItem != null) {
			checkCartItem.setQuantity(checkCartItem.getQuantity()+quantity);
			this.cartItemService.add(checkCartItem);
		}else {
			cartItem.setProduct(this.productService.findById(proId));
			cartItem.setQuantity(quantity);
			this.cartItemService.add(cartItem);
		}

		return "redirect:/cart";
	}
		

	@GetMapping("/delete-product/{id}")
	public String delete(@PathVariable("id") Integer id) {
		if (this.cartItemService.delete(id)) {
			return "redirect:/cart";
		}
		return "user/cart";
	}
	
	@GetMapping("/deleteAll/{id}")
	public String deleteAll(@PathVariable("id") Integer id) {
		this.cartItemService.deleteByCartId(id);
		return "redirect:/cart";
	}
	
	
	
	
	
	
}
