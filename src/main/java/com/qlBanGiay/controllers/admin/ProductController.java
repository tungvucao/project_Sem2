package com.qlBanGiay.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qlBanGiay.entities.Category;
import com.qlBanGiay.entities.Product;
import com.qlBanGiay.service.CategoryService;
import com.qlBanGiay.service.ProductService;
import com.qlBanGiay.service.StorageService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/product")
	public String index(Model model,@Param("keyword") String keyword,@RequestParam(name="pageNo",defaultValue = "1") Integer pageNo) {
		Page<Product> listPro = this.productService.getPage(pageNo);
		if (keyword != null) {
			listPro = this.productService.findProByName(keyword,pageNo);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("totalPage", listPro.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("listPro", listPro);
		return "admin/product/index";
	}	
	
	@GetMapping("/add-product")
	public String add(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		List<Category> listCate = this.categoryService.findAll();
		model.addAttribute("listCate", listCate);
		return "admin/product/add";
	}
	
	@PostMapping("/add-product")
	public String save(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file) {
		//upload
		this.storageService.store(file);
		String fileName = file.getOriginalFilename(); 
		product.setImage(fileName);
		if (product.getPublish() == null) {
			product.setPublish(LocalDateTime.now());
		}
		if (this.productService.createOrUpdate(product)) {
			return "redirect:/admin/product";		
		}
		return "admin/product/add";
	}
	
	@GetMapping("/edit-product/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Product product = this.productService.findById(id);
		model.addAttribute("product", product);
		List<Category> listCate = this.categoryService.findAll();
		model.addAttribute("listCate", listCate);
		return "admin/product/edit";
	}
	
	@PostMapping("/edit-product")
	public String updatePro(@ModelAttribute("product") Product product, @RequestParam("fileImage")MultipartFile file) {
		
		String fileName = file.getOriginalFilename();
		boolean isEmpty = fileName == null || fileName.trim().length() == 0;
		if(!isEmpty) {
			this.storageService.store(file);
			product.setImage(fileName);
		}
		if (product.getPublish() == null) {
			product.setPublish(LocalDateTime.now());
		}
		
		if(this.productService.createOrUpdate(product)) {
			return "redirect:/admin/product";
		} else {
			return "admin/product/edit";
		}
	}
	
	
	@GetMapping("/delete-product/{id}")
	public String delete(@PathVariable("id") Integer id) {
		if (this.productService.delete(id)) {
			return "redirect:/admin/product";
		}
		return "admin/product/index";
	}
	
}