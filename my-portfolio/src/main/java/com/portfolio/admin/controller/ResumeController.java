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

import com.portfolio.admin.dto.ResumeDTO;
import com.portfolio.admin.service.IResumeService;
import com.portfolio.common.constant.SystemConstant;

@Controller
@RequestMapping(value = "/admin/resume")
public class ResumeController extends BaseController {
	@Autowired
	private IResumeService resumeService;

	@GetMapping
	public String showDetailsPage(Model model) {
		return redirectPage("details", model);
	}

	@GetMapping(value = "/edit")
	public String showEditPage(Model model) {
		return redirectPage("edit", model);
	}

	private String redirectPage(String action, Model model) {
		try {
			if ("details" == action) {
				setViewTitleAndFavicon(SystemConstant.RESUME_TITLE_DETAILS_PAGE, model);
			} else {
				setViewTitleAndFavicon(SystemConstant.RESUME_TITLE_EDIT_PAGE, model);
			}
			model.addAttribute("resumeDTO", resumeService.getDetails(SystemConstant.RESUME_ID));
		} catch (Exception e) {
			return "rediect:/admin/error";
		}
		return "/admin/resume/" + action;
	}

	@PostMapping(value = "/edit")
	public String handleEditFeature(@Valid @ModelAttribute("resumeDTO") ResumeDTO dto, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		if (bindingResult.hasErrors()) {
			setViewTitleAndFavicon(SystemConstant.RESUME_TITLE_EDIT_PAGE, model);
			model.addAttribute("resumeDTO", dto);
			return "/admin/resume/edit";
		}
		if (resumeService.save(dto) != null) {
			notification(SystemConstant.UPDATE_SUCCESSFUL, SystemConstant.SUCCESS_ALERT, redirectModel);
		} else {
			notification(SystemConstant.UNSUCCESSFUL_UPDATE, SystemConstant.DANGER_ALERT, redirectModel);
		}
		return "redirect:/admin/resume";
	}
}
