package com.portfolio.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController extends BaseController {
	@GetMapping(value = "/sign-in")
	public String viewSignInPage(Model model) {
		setViewTitleAndFavicon("Sign in", model);
		return "/admin/sign-in";
	}
}
