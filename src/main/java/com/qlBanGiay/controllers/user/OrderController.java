package com.qlBanGiay.controllers.user;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qlBanGiay.entities.Cart;
import com.qlBanGiay.entities.CartItem;
import com.qlBanGiay.entities.Category;
import com.qlBanGiay.entities.CustomUserDetails;
import com.qlBanGiay.entities.Orders;
import com.qlBanGiay.entities.OrderDetail;
import com.qlBanGiay.service.CartItemService;
import com.qlBanGiay.service.CartService;
import com.qlBanGiay.service.CategoryService;
import com.qlBanGiay.service.OrderDetailService;
import com.qlBanGiay.service.OrderService;
import com.qlBanGiay.service.ProductService;

@Controller
public class OrderController {
	@Autowired
	private CartItemService cartItemService ;
	@Autowired
	private CartService cartService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	
	
	@GetMapping("/checkout")
	public String checkout( Principal principal,Model model,RedirectAttributes redirectAttributes) {
		if (principal == null) {
			return "redirect:/login";
		}
		
		List<Category> allCategory = this.categoryService.findAll();
		List<Category> allCategoryTrue = new ArrayList<Category>();
		for (Category category : allCategory) {
			if (category.getCateStatus() == true) {
				allCategoryTrue.add(category);
			}
		}
		model.addAttribute("allCategoryTrue", allCategoryTrue);
		
		CustomUserDetails user =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = this.cartService.findByUser(user.getUser());
		if (cart.getCartItems().size() == 0) {
			redirectAttributes.addFlashAttribute("messCartItem", "Bạn chưa có sản phẩm nào trong giỏ hàng!");
			return "redirect:/cart";
		}
		model.addAttribute("user", user.getUser());
		model.addAttribute("listCart", cart);
		model.addAttribute("totalPrice", cart.totalPrice());
		
		Orders orders = new Orders();
		if (orders.getCreatedAt() == null) {
			orders.setCreatedAt(LocalDateTime.now());
		}
		orders.setUser(user.getUser());
		model.addAttribute("order", orders);
		
		return "user/checkout";
	}
	
	@PostMapping("/postCheckOut")
	public String postCheckOut( Principal principal,Model model,@ModelAttribute("order") Orders orders ) {
		if (principal == null) {
			return "redirect:/login";
		}
		
		List<Category> allCategory = this.categoryService.findAll();
		List<Category> allCategoryTrue = new ArrayList<Category>();
		for (Category category : allCategory) {
			if (category.getCateStatus() == true) {
				allCategoryTrue.add(category);
			}
		}
		model.addAttribute("allCategoryTrue", allCategoryTrue);
		
		CustomUserDetails user =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = this.cartService.findByUser(user.getUser());
		
		orders.setUser(user.getUser());
		orders.setStatus(0);
		if (orders.getCreatedAt() == null) {
			orders.setCreatedAt(LocalDateTime.now());
		}
		if (this.orderService.create(orders)) {
			for (CartItem cartItem : cart.getCartItems()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrder(orders);
				orderDetail.setProduct(cartItem.getProduct());
				orderDetail.setQuantity(cartItem.getQuantity());
				if (cartItem.getProduct().getSalePrice() != 0) {
					orderDetail.setPrice(cartItem.getProduct().getSalePrice());
				}else {
					orderDetail.setPrice(cartItem.getProduct().getPrice());					
				}
				
				this.orderDetailService.add(orderDetail);
			}
		}
		this.cartItemService.deleteByCartId(cart.getId());
		
		return "user/thank";
	}
}
