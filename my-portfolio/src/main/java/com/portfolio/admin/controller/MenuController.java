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

import com.portfolio.admin.dto.MenuDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.paging.PagingParam;
import com.portfolio.admin.service.IMenuService;
import com.portfolio.common.constant.SystemConstant;

@Controller
@RequestMapping(value = "/admin/menu")
public class MenuController extends BaseController {
	@Autowired
	private IMenuService menuService;

	@GetMapping
	public String showListPage(
			@PagingParam(path = "menu", orderBy = "name", orderType = "asc") ResponseDataTableDTO responseDataTableDTO,
			Model model) {
		try {
			setViewTitleAndFavicon(SystemConstant.MENU_TITLE_LIST_PAGE, model);
			ResponseDataTableDTO resultList = menuService.getList(responseDataTableDTO);
			model.addAttribute("resultList", resultList);
			model.addAttribute("totalItemsFound", resultList.getTotalOfItem());
			String getKeyword = responseDataTableDTO.getKeyword();
			if (getKeyword != null) {
				model.addAttribute("keyword", getKeyword);
			}
			String getOrderTypeParam = responseDataTableDTO.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc";
			model.addAttribute("orderType", getOrderTypeParam);
			return "/admin/menu/list";
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
	}

	@GetMapping(value = "/create")
	public String showCreatePage(Model model) {
		setViewTitleAndFavicon(SystemConstant.MENU_TITLE_CREATE_PAGE, model);
		model.addAttribute("menuDTO", new MenuDTO());
		model.addAttribute("action", SystemConstant.CREATE);
		return "/admin/menu/create-or-edit";
	}

	@PostMapping(value = "/create")
	public String handleCreateFeature(@Valid @ModelAttribute("menuDTO") MenuDTO dto, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save(SystemConstant.CREATE, dto, bindingResult, redirectModel, model);
	}

	@GetMapping(value = "/edit")
	public String showEditPage(@RequestParam(value = "id", required = false) String id, Model model) {
		setViewTitleAndFavicon(SystemConstant.MENU_TITLE_EDIT_PAGE, model);
		model.addAttribute("action", SystemConstant.EDIT);
		try {
			model.addAttribute("menuDTO", menuService.getDetails(id));
			return "/admin/menu/create-or-edit";
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
	}

	@PostMapping(value = "/edit")
	public String handleEditFeature(@Valid @ModelAttribute("menuDTO") MenuDTO dto, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save(SystemConstant.EDIT, dto, bindingResult, redirectModel, model);
	}

	private String save(String action, MenuDTO dto, BindingResult bindingResult, RedirectAttributes redirectModel,
			Model model) {
		if (bindingResult.hasErrors()) {
			return checkDTOValidation(action, dto, model);
		}
		if (menuService.save(dto) != null) {
			if (action.equals(SystemConstant.CREATE)) {
				notification(SystemConstant.SUCCESSFUL_INSERT, SystemConstant.SUCCESS_ALERT, redirectModel);
			} else {
				notification(SystemConstant.UPDATE_SUCCESSFUL, SystemConstant.SUCCESS_ALERT, redirectModel);
			}
		} else {
			model.addAttribute("duplicateErr", SystemConstant.DUPLICATE_ERR);
			return checkDTOValidation(action, dto, model);
		}
		return "redirect:/admin/menu";
	}

	private String checkDTOValidation(String action, MenuDTO dto, Model model) {
		String viewTitle = null;
		if (action.equals(SystemConstant.CREATE)) {
			viewTitle = SystemConstant.MENU_TITLE_CREATE_PAGE;
		} else {
			viewTitle = SystemConstant.MENU_TITLE_EDIT_PAGE;
		}
		setViewTitleAndFavicon(viewTitle, model);
		model.addAttribute("menuDTO", dto);
		model.addAttribute("action", action);
		return "/admin/menu/create-or-edit";
	}
}
