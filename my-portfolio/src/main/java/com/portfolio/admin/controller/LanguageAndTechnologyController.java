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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.admin.dto.LanguageAndTechnologyDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.paging.PagingParam;
import com.portfolio.admin.service.ILTService;
import com.portfolio.common.constant.SystemConstant;

@Controller
@RequestMapping(value = "/admin/language-and-technology")
public class LanguageAndTechnologyController extends BaseController {
	@Autowired
	private ILTService ltService;

	@GetMapping
	public String showListPage(
			@PagingParam(path = "language-and-technology", orderBy = "name", orderType = "asc") ResponseDataTableDTO responseDataTableDTO,
			Model model) {
		try {
			setViewTitleAndFavicon(SystemConstant.LT_TITLE_LIST_PAGE, model);
			ResponseDataTableDTO resultList = ltService.getList(responseDataTableDTO);
			model.addAttribute("resultList", resultList);
			model.addAttribute("totalItemsFound", resultList.getTotalOfItem());
			String getKeyword = responseDataTableDTO.getKeyword();
			if (getKeyword != null) {
				model.addAttribute("keyword", getKeyword);
			}
			model.addAttribute("orderType",
					responseDataTableDTO.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc");
			return "/admin/language-technology/list";
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
	}

	@GetMapping(value = "/create")
	public String showCreatePage(Model model) {
		setViewTitleAndFavicon(SystemConstant.LT_TITLE_CREATE_PAGE, model);
		model.addAttribute("action", SystemConstant.CREATE);
		model.addAttribute("ltDTO", new LanguageAndTechnologyDTO());
		return "/admin/language-technology/create-or-edit";
	}

	@PostMapping(value = "/create")
	public String handleCreateFeature(@Valid @ModelAttribute("ltDTO") LanguageAndTechnologyDTO dto,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		return save(SystemConstant.CREATE, dto, bindingResult, redirectModel, model);
	}

	@GetMapping(value = "/edit")
	public String showEditPage(@RequestParam(value = "id", required = false) String id, Model model) {
		setViewTitleAndFavicon(SystemConstant.LT_TITLE_EDIT_PAGE, model);
		try {
			model.addAttribute("ltDTO", ltService.getDetails(id));
			model.addAttribute("action", SystemConstant.EDIT);
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
		return "/admin/language-technology/create-or-edit";
	}

	@PostMapping(value = "/edit")
	public String handleEditFeature(@Valid @ModelAttribute("ltDTO") LanguageAndTechnologyDTO dto,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		return save(SystemConstant.EDIT, dto, bindingResult, redirectModel, model);
	}

	private String save(String action, LanguageAndTechnologyDTO dto, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		if (bindingResult.hasErrors()) {
			return checkDTOValidation(action, dto, model);
		}
		if (ltService.save(dto) != null) {
			if (action.equals(SystemConstant.CREATE)) {
				notification(SystemConstant.SUCCESSFUL_INSERT, SystemConstant.SUCCESS_ALERT, redirectModel);
			} else {
				notification(SystemConstant.UPDATE_SUCCESSFUL, SystemConstant.SUCCESS_ALERT, redirectModel);
			}
		} else {
			model.addAttribute("duplicateErr", SystemConstant.DUPLICATE_ERR);
			return checkDTOValidation(action, dto, model);
		}
		return "redirect:/admin/language-and-technology";
	}

	private String checkDTOValidation(String action, LanguageAndTechnologyDTO dto, Model model) {
		String viewTitle = null;
		if (action.equals(SystemConstant.CREATE)) {
			viewTitle = SystemConstant.LT_TITLE_CREATE_PAGE;
		} else {
			viewTitle = SystemConstant.LT_TITLE_EDIT_PAGE;
		}
		setViewTitleAndFavicon(viewTitle, model);
		model.addAttribute("ltDTO", dto);
		model.addAttribute("action", action);
		return "/admin/language-technology/create-or-edit";
	}
}
