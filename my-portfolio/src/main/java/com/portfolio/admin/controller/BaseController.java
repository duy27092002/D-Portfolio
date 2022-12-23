package com.portfolio.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.admin.service.IWebsiteService;
import com.portfolio.common.constant.SystemConstant;

@Controller
public class BaseController {
	@Autowired
	private IWebsiteService websiteService;

	protected void setViewTitleAndFavicon(String viewTitle, Model model) {
		model.addAttribute("viewTitle", viewTitle);
		model.addAttribute("favicon", websiteService.getDetails(SystemConstant.WEBSITE_ID).getFavicon());
	}

	protected void notification(String mess, String typeAlert, RedirectAttributes redirectModel) {
		redirectModel.addFlashAttribute("mess", mess);
		redirectModel.addFlashAttribute("typeAlert", typeAlert);
	}
}
