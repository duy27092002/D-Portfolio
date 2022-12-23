package com.portfolio.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.admin.dto.AboutMeDTO;
import com.portfolio.admin.dto.ChangePasswordDTO;
import com.portfolio.admin.service.IAboutMeService;
import com.portfolio.common.constant.SystemConstant;

@Controller
@RequestMapping(value = "/admin/my-profile")
public class AboutMeController extends BaseController {
	@Autowired
	private IAboutMeService aboutMeService;

	@GetMapping
	public String viewDetailsPage(Model model) {
		setViewTitleAndFavicon(SystemConstant.ABOUT_ME_TITLE_DETAILS_PAGE, model);
		model.addAttribute("aboutMeDetails", aboutMeService.getDetails(SystemConstant.ABOUT_ME_ID));
		return "/admin/about-me/details";
	}

	@GetMapping(value = "/edit")
	public String viewEditPage(Model model) {
		setViewTitleAndFavicon(SystemConstant.ABOUT_ME_TITLE_EDIT_PAGE, model);
		model.addAttribute("aboutMeDTO", aboutMeService.getDetails(SystemConstant.ABOUT_ME_ID));
		return "/admin/about-me/edit";
	}

	@PostMapping(value = "/edit")
	public String updateProfile(@Valid @ModelAttribute("aboutMeDTO") AboutMeDTO dto, BindingResult bindingResult,
			Model model, RedirectAttributes redirectModel) {
		if (bindingResult.hasErrors()) {
			setViewTitleAndFavicon(SystemConstant.ABOUT_ME_TITLE_EDIT_PAGE, model);
			model.addAttribute("aboutMeDTO", dto);
			return "/admin/about-me/edit";
		}

		if (aboutMeService.save(dto) != null) {
			notification(SystemConstant.UPDATE_SUCCESSFUL, SystemConstant.SUCCESS_ALERT, redirectModel);
		} else {
			notification(SystemConstant.UNSUCCESSFUL_UPDATE, SystemConstant.DANGER_ALERT, redirectModel);
		}

		return "redirect:/admin/my-profile";
	}

	@GetMapping(value = "/change-password")
	public String showChangePasswordPage(Model model) {
		setViewTitleAndFavicon(SystemConstant.TITLE_OF_CHANGE_PASSWORD_PAGE, model);
		model.addAttribute("changePasswordDTO", new ChangePasswordDTO());
		return "/admin/about-me/change-password";
	}

	@PostMapping(value = "/change-password")
	public String handleChangePasswordFeature(@Valid @ModelAttribute("changePasswordDTO") ChangePasswordDTO dto,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		if (bindingResult.hasErrors()) {
			setViewTitleAndFavicon(SystemConstant.TITLE_OF_CHANGE_PASSWORD_PAGE, model);
			model.addAttribute("changePasswordDTO", dto);
			return "/admin/about-me/change-password";
		}
		if (aboutMeService.checkOldPassword(dto.getOldPassword())) {
			if (aboutMeService.changePassword(dto)) {
				notification("Change password successfully", SystemConstant.SUCCESS_ALERT, redirectModel);
			} else {
				notification("Change password unsuccessfully", SystemConstant.DANGER_ALERT, redirectModel);
			}
		} else {
			setViewTitleAndFavicon(SystemConstant.TITLE_OF_CHANGE_PASSWORD_PAGE, model);
			model.addAttribute("changePasswordDTO", dto);
			model.addAttribute("invalidOldPass", "Old password does not match");
			return "/admin/about-me/change-password";
		}
		return "redirect:/admin/my-profile";
	}
}
