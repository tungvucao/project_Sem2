package com.qlBanGiay.controllers.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlBanGiay.entities.Category;
import com.qlBanGiay.entities.Product;
import com.qlBanGiay.service.CategoryService;
import com.qlBanGiay.service.ProductService;
import com.qlBanGiay.service.UserService;

@Controller
@RequestMapping(value = "")
public class HomeController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String home(Model model) {
		// Get Danh mục true
		List<Category> listCate = this.categoryService.findAll();
		List<Category> listCateTrue = new ArrayList<Category>();
		for (Category category : listCate) {
			if (category.getCateStatus() == true) {
				listCateTrue.add(category);
			}
		}
		
		model.addAttribute("listCateTrue", listCateTrue);
		
		// Get Sale Product
		List<Product> saleProduct = this.productService.findProSale50();
		model.addAttribute("saleProduct", saleProduct);
		
		// Get New Product
		List<Product> newProduct = this.productService.findNewProduct();
		model.addAttribute("newProduct", newProduct);
		
		return "user/index";
		
	}
}
