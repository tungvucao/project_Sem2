package com.qlBanGiay.controllers.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qlBanGiay.entities.Category;
import com.qlBanGiay.entities.Product;
import com.qlBanGiay.service.CategoryService;
import com.qlBanGiay.service.ProductService;

@Controller
public class HomeProductController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	@GetMapping("/detail/{id}")
	public String detailPro(@PathVariable("id") Integer id, Model model) {
		// Get Danh Mục theo Id
//		Category cateById = this.categoryService.findById(id);
//		model.addAttribute("cateById", cateById);

		// Get Danh mục true
		List<Category> allCategory = this.categoryService.findAll();
		List<Category> allCategoryTrue = new ArrayList<Category>();
		for (Category category : allCategory) {
			if (category.getCateStatus() == true) {
				allCategoryTrue.add(category);
			}
		}
		model.addAttribute("allCategoryTrue", allCategoryTrue);

		// Get Product by Id
		Product product = this.productService.findById(id);
		model.addAttribute("product", product);

		// Get List Product by Cate Id
		Integer cateId = product.getCategory().getId();
		List<Product> listProByCateId = this.productService.findProByCateId(cateId, id);
		model.addAttribute("listProByCateId", listProByCateId);
		return "user/detail";
	}

	@RequestMapping("/list/product")
	public String listProduct(Model model, @Param("keyword") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "direction", required = false) String direction) {
		// Get all Category True
		List<Category> allCategory = this.categoryService.findAll();
		List<Category> allCategoryTrue = new ArrayList<Category>();
		for (Category category : allCategory) {
			if (category.getCateStatus() == true) {
				allCategoryTrue.add(category);
			}
		}
		model.addAttribute("allCategoryTrue", allCategoryTrue);
		
		// Get all Product 
		String sortField = sortBy == null ? "product_id" : sortBy;
		Sort sort = (direction == null || direction.equals("asc")) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Page<Product> allProductTrue = this.productService.getPageProTrueUser(pageNo,sort);
		if (keyword != null) {
			allProductTrue = this.productService.findProTrueByName(keyword, pageNo,sort);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("totalPage", allProductTrue.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("direction", direction);
		model.addAttribute("allProductTrue", allProductTrue);
		
		return "user/list";
	}
	
	@GetMapping("/list/product/{id}")
	public String listProByCateId(@PathVariable("id") Integer id, Model model, @Param("keyword") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "direction", required = false) String direction) {
		// Get all Category True
		List<Category> allCategory = this.categoryService.findAll();
		List<Category> allCategoryTrue = new ArrayList<Category>();
		for (Category category : allCategory) {
			if (category.getCateStatus() == true) {
				allCategoryTrue.add(category);
			}
		}
		model.addAttribute("allCategoryTrue", allCategoryTrue);
		
		//Get All Sale Product
		String sortField = sortBy == null ? "product_id" : sortBy;
		Sort sort = (direction == null || direction.equals("asc")) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Page<Product> allProduct2 = this.productService.getPageUserAllPro(id, pageNo,sort);
		if (keyword != null) {
			allProduct2 = this.productService.findProByNameAndCateId(id, keyword, pageNo,sort);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("totalPage", allProduct2.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("allProduct2", allProduct2);

		return "user/listByCateId";
	}

	@GetMapping("/list/sale")
	public String salePro(Model model, @Param("keyword") String keyword,
			@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "direction", required = false) String direction) {
		// Get all Category True
		List<Category> allCategory = this.categoryService.findAll();
		List<Category> allCategoryTrue = new ArrayList<Category>();
		for (Category category : allCategory) {
			if (category.getCateStatus() == true) {
				allCategoryTrue.add(category);
			}
		}
		model.addAttribute("allCategoryTrue", allCategoryTrue);
		
		//Get All Sale Product
		String sortField = sortBy == null ? "product_id" : sortBy;
		Sort sort = (direction == null || direction.equals("asc")) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Page<Product> saleProduct = this.productService.getPageUserSale(pageNo, sort);
		if (keyword != null) {
			saleProduct = this.productService.findProSale50ByName(keyword, pageNo, sort);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("totalPage", saleProduct.getTotalPages());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("saleProduct", saleProduct);

		return "user/sale";
	}
	
}
