package com.portfolio.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController extends BaseController {
	@GetMapping(value = "/admin/error")
	public String showErrorPage(Model model) {
		setViewTitleAndFavicon("404", model);
		return "/common/404";
	}
}
