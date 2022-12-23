package com.portfolio.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "HomeControllerOfAdmin")
@RequestMapping(value = "/admin")
public class HomeController extends BaseController {
	@GetMapping(value = "/home")
	public String viewDashboardPage(Model model) {
		setViewTitleAndFavicon("Dashboard", model);
		return "/admin/home/index";
	}
}
