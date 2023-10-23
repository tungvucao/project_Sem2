package com.qlBanGiay.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@RequestMapping("/logon")
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("messLog", "Đăng nhập thất bại!");
		}
		return "admin/logon";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("messLog", "Đã đăng xuất!");
		return "admin/logon";
	}
}
