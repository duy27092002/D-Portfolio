package com.portfolio.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.admin.dto.WebsiteDTO;
import com.portfolio.admin.service.IWebsiteService;
import com.portfolio.common.constant.SystemConstant;

@Controller
@RequestMapping(value = "/admin/website")
public class WebsiteController extends BaseController {
	@Autowired
	private IWebsiteService websiteService;

	@GetMapping
	public String showDetailsPage(Model model) {
		setViewTitleAndFavicon("Website information", model);
		model.addAttribute("websiteInfo", websiteService.getDetails(SystemConstant.WEBSITE_ID));
		return "/admin/website/details";
	}

	@GetMapping(value = "/edit")
	public String showEditPage(Model model) {
		setViewTitleAndFavicon("Edit website information", model);
		model.addAttribute("websiteDTO", websiteService.getDetails(SystemConstant.WEBSITE_ID));
		return "/admin/website/edit";
	}

	@PostMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String handleEditFeature(@Valid @ModelAttribute("websiteDTO") WebsiteDTO dto, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		if (bindingResult.hasErrors()) {
			setViewTitleAndFavicon("Edit website information", model);
			model.addAttribute("websiteDTO", dto);
			WebsiteDTO getWebsiteInfo = websiteService.getDetails(SystemConstant.WEBSITE_ID);
			model.addAttribute("oldFavicon", getWebsiteInfo.getFavicon());
			model.addAttribute("oldLogo", getWebsiteInfo.getLogo());
			return "/admin/website/edit";
		}
		
		if (websiteService.save(dto) != null) {
			notification(SystemConstant.UPDATE_SUCCESSFUL, SystemConstant.SUCCESS_ALERT, redirectModel);
		} else {
			notification(SystemConstant.UNSUCCESSFUL_UPDATE, SystemConstant.DANGER_ALERT, redirectModel);
		}
		return "redirect:/admin/website";
	}
}
