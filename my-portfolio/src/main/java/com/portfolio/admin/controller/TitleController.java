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

import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.dto.TitleDTO;
import com.portfolio.admin.dto.IdParameterDTO;
import com.portfolio.admin.paging.PagingParam;
import com.portfolio.admin.service.ITitleService;
import com.portfolio.common.constant.SystemConstant;

@Controller
@RequestMapping(value = "/admin/title")
public class TitleController extends BaseController {
	@Autowired
	private ITitleService titleService;

	@GetMapping
	public String showListPage(
			@PagingParam(path = "title", orderBy = "titleIndex", orderType = "desc") ResponseDataTableDTO responseDataTableDTO,
			Model model) {
		try {
			setViewTitleAndFavicon(SystemConstant.T_TITLE_LIST_PAGE, model);
			ResponseDataTableDTO resultList = titleService.getList(responseDataTableDTO);
			model.addAttribute("totalItemsFound", resultList.getTotalOfItem());
			model.addAttribute("resultList", resultList);
			String getKeywordParam = responseDataTableDTO.getKeyword();
			if (getKeywordParam != null) {
				model.addAttribute("keyword", getKeywordParam);
			}
			model.addAttribute("orderType",
					responseDataTableDTO.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc");
			return "/admin/title/list";
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
	}

	@GetMapping(value = "/create")
	public String showCreatePage(Model model) {
		setViewTitleAndFavicon(SystemConstant.T_TITLE_CREATE_PAGE, model);
		model.addAttribute("action", SystemConstant.CREATE);
		model.addAttribute("titleDTO", new TitleDTO());
		return "/admin/title/create-or-edit";
	}

	@PostMapping(value = "/create")
	public String handleCreateFeature(@Valid @ModelAttribute("titleDTO") TitleDTO dto, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save(SystemConstant.CREATE, dto, bindingResult, redirectModel, model);
	}

	@GetMapping(value = "/edit")
	public String showEditPage(@RequestParam(value = "id", required = false) String id, Model model) {
		setViewTitleAndFavicon(SystemConstant.T_TITLE_EDIT_PAGE, model);
		model.addAttribute("action", SystemConstant.EDIT);
		try {
			model.addAttribute("titleDTO", titleService.getDetails(id));
			return "/admin/title/create-or-edit";
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
	}

	@PostMapping(value = "/edit")
	public String handleEditFeature(@Valid @ModelAttribute("titleDTO") TitleDTO dto, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save(SystemConstant.EDIT, dto, bindingResult, redirectModel, model);
	}

	private String save(String action, TitleDTO dto, BindingResult bindingResult, RedirectAttributes redirectModel,
			Model model) {
		if (titleService.titleIndexWasExisted(dto, dto.getTitleIndex())) {
			model.addAttribute("titleIndexInvalid", "This index was existed");
			return checkDTOValidation(action, dto, model);
		} else {
			if (bindingResult.hasErrors()) {
				return checkDTOValidation(action, dto, model);
			}
			if (titleService.save(dto) != null) {
				if (action.equals(SystemConstant.CREATE)) {
					notification(SystemConstant.SUCCESSFUL_INSERT, SystemConstant.SUCCESS_ALERT, redirectModel);
				} else {
					notification(SystemConstant.UPDATE_SUCCESSFUL, SystemConstant.SUCCESS_ALERT, redirectModel);
				}
			} else {
				model.addAttribute("duplicateErr", SystemConstant.DUPLICATE_ERR);
				return checkDTOValidation(action, dto, model);
			}
		}
		return "redirect:/admin/title";
	}

	private String checkDTOValidation(String action, TitleDTO dto, Model model) {
		String viewTitle = null;
		if (action.equals(SystemConstant.CREATE)) {
			viewTitle = SystemConstant.T_TITLE_CREATE_PAGE;
		} else {
			viewTitle = SystemConstant.T_TITLE_EDIT_PAGE;
		}
		setViewTitleAndFavicon(viewTitle, model);
		model.addAttribute("titleDTO", dto);
		model.addAttribute("action", action);
		return "/admin/title/create-or-edit";
	}

	@GetMapping(value = "/change-index-position")
	public String showChangeIndexPositionPage(Model model) {
		return setUpChangeTheIndexPositionPage(new IdParameterDTO(), model);
	}

	@PostMapping(value = "/change-index-position")
	public String handleChangeIndexPositionFeature(@Valid @ModelAttribute("titleId") IdParameterDTO dto,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		if (bindingResult.hasErrors()) {
			return setUpChangeTheIndexPositionPage(dto, model);
		}
		if (titleService.changeIndexPosition(dto)) {
			notification(SystemConstant.CHANGE_INDEX_SUCCESSFULLY, SystemConstant.SUCCESS_ALERT, redirectModel);
		} else {
			notification(SystemConstant.CHANGE_INDEX_UNSUCCESSFULLY, SystemConstant.SUCCESS_ALERT, redirectModel);
		}
		return "redirect:/admin/title";
	}

	private String setUpChangeTheIndexPositionPage(IdParameterDTO dto, Model model) {
		setViewTitleAndFavicon(SystemConstant.CHANGE_INDEX_POSITION_PAGE, model);
		model.addAttribute("titleId", dto);
		model.addAttribute("activeTitleList", titleService.getTitleListByStatus(1));
		return "/admin/title/change-index-position";
	}
}
