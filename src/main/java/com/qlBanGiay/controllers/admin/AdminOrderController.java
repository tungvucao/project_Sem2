package com.qlBanGiay.controllers.admin;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qlBanGiay.entities.OrderDetail;
import com.qlBanGiay.entities.Orders;
import com.qlBanGiay.entities.User;
import com.qlBanGiay.service.OrderDetailService;
import com.qlBanGiay.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	
	@GetMapping("/order")
	public String getAll(Model model) {
		List<Orders> orders = this.orderService.findAll();
		model.addAttribute("listOrder", orders);
		return "admin/order/index";
	}
	
	@GetMapping("/detail/{id}")
	public String orderDetail(Model model, @PathVariable("id") Integer orderId) {
		
		Orders order = this.orderService.findById(orderId);
		List<OrderDetail> listOrderDetail = this.orderDetailService.findByOrders(order);
		User user = order.getUser();
		LocalDateTime time = LocalDateTime.now();
		
		model.addAttribute("time", time);
		model.addAttribute("user", user);
		model.addAttribute("order", order);
		model.addAttribute("listOrderDetail", listOrderDetail);
		return "admin/order/detail";
	}
	
	@PostMapping("/updateOrder")
	public String update(Model model, @RequestParam("id") Integer id,@RequestParam("status") Integer status) {
		
		Orders order = this.orderService.findById(id);
		order.setStatus(status);
		this.orderService.create(order);
		return "redirect:/admin/order";
	}
}
