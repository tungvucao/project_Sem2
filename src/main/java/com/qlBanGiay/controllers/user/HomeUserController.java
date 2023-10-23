package com.qlBanGiay.controllers.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qlBanGiay.entities.Cart;
import com.qlBanGiay.entities.Category;
import com.qlBanGiay.entities.CustomUserDetails;
import com.qlBanGiay.entities.OrderDetail;
import com.qlBanGiay.entities.Orders;
import com.qlBanGiay.entities.User;
import com.qlBanGiay.service.CartService;
import com.qlBanGiay.service.CategoryService;
import com.qlBanGiay.service.OrderDetailService;
import com.qlBanGiay.service.UserService;

import jakarta.validation.Valid;

@Controller
public class HomeUserController {
	
	@Autowired
	private UserService service;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private CartService cartService;
	
	@GetMapping("/account")
	public String account(Model model, Principal principal) {
		if (principal == null) {
			return "redirect:/login";
		}
		CustomUserDetails user =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user.getUser());

		List<Category> allCategory = this.categoryService.findAll();
		List<Category> allCategoryTrue = new ArrayList<Category>();
		for (Category category : allCategory) {
			if (category.getCateStatus() == true) {
				allCategoryTrue.add(category);
			}
		}
		model.addAttribute("allCategoryTrue", allCategoryTrue);
		
		return "/user/account";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(Model model,@ModelAttribute("user") @Valid User user,BindingResult result) {
		CustomUserDetails ustomUserDetails =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user2 = ustomUserDetails.getUser();
		System.out.println(user.getFullName());
		System.out.println(user.getUserName());
		System.out.println(user.getGender());
		System.out.println(user.getBirthday());
		if (result.hasErrors()) {
			System.out.println("Có lỗi");
			return "redirect:/account";
		}
		user2.setEnabled(true);
		user2.setFullName(user.getFullName());
		user2.setAddress(user.getAddress());
		user2.setBirthday(user.getBirthday());
		user2.setGender(user.getGender());
		user2.setEmail(user.getEmail());
		this.service.create(user2);
		return "/user/account";
	}
	
	@GetMapping("/login")
	public String loginUser(Model model) {
		List<Category> allCategory = this.categoryService.findAll();
		List<Category> allCategoryTrue = new ArrayList<Category>();
		for (Category category : allCategory) {
			if (category.getCateStatus() == true) {
				allCategoryTrue.add(category);
			}
		}
		model.addAttribute("allCategoryTrue", allCategoryTrue);
		return "user/login";
	}
	
	
	@GetMapping("/register")
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		List<Category> allCategory = this.categoryService.findAll();
		List<Category> allCategoryTrue = new ArrayList<Category>();
		for (Category category : allCategory) {
			if (category.getCateStatus() == true) {
				allCategoryTrue.add(category);
			}
		}
		model.addAttribute("allCategoryTrue", allCategoryTrue);
		return "user/register";
	}
	
	@PostMapping("/register")
	public String doRegister(Model model, @ModelAttribute @Valid User user,BindingResult result) {
		if (result.hasErrors()) {
			return "user/register";
		}
		if (this.service.countUserName(user.getUserName()) >= 1) {
			model.addAttribute("messError", "User Name đã có người sử dụng!");
			return "redirect:/register";
		}
		
		String hasPass = new BCryptPasswordEncoder().encode(user.getPassWord());
		user.setPassWord(hasPass);
		user.setEnabled(true);
		if (this.service.create(user)) {
			model.addAttribute("mess", "Đăng ký thành công!");
		}
		return "user/login";
	}
}
