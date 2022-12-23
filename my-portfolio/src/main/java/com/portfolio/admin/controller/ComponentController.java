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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.admin.dto.ComponentDTO;
import com.portfolio.admin.dto.IdParameterDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.paging.PagingParam;
import com.portfolio.admin.service.IComponentService;
import com.portfolio.admin.service.ITitleService;
import com.portfolio.common.constant.SystemConstant;

@Controller
@RequestMapping(value = "/admin/component")
public class ComponentController extends BaseController {
	@Autowired
	private IComponentService componentService;

	@Autowired
	private ITitleService titleService;

	@GetMapping
	public String showListPage(
			@PagingParam(path = "component", orderBy = "cIndex") ResponseDataTableDTO responseDataTableDTO,
			Model model) {
		try {
			setViewTitleAndFavicon(SystemConstant.COMPONENT_TITLE_LIST_PAGE, model);
			ResponseDataTableDTO resultList = componentService.getList(responseDataTableDTO);
			model.addAttribute("totalItemsFound", resultList.getTotalOfItem());
			model.addAttribute("resultList", resultList);
			String getKeywordParam = responseDataTableDTO.getKeyword();
			if (getKeywordParam != null) {
				model.addAttribute("keyword", getKeywordParam);
			}
			model.addAttribute("orderType",
					responseDataTableDTO.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc");
			return "/admin/component/list";
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
	}

	@GetMapping(value = "/create")
	public String showCreatePage(Model model) {
		setViewTitleAndFavicon(SystemConstant.COMPONENT_TITLE_CREATE_PAGE, model);
		model.addAttribute("action", SystemConstant.CREATE);
		model.addAttribute("componentDTO", new ComponentDTO());
		model.addAttribute("activeTitleList", titleService.getTitleListByStatus(1));
		return "/admin/component/create-or-edit";
	}

	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String handleCreateFeature(@ModelAttribute("componentDTO") ComponentDTO dto,
			RedirectAttributes redirectModel, Model model) {
		return save(SystemConstant.CREATE, dto, redirectModel, model);
	}

	@GetMapping(value = "/edit")
	public String showEditPage(@RequestParam(value = "id", required = false) String id, Model model) {
		setViewTitleAndFavicon(SystemConstant.COMPONENT_TITLE_EDIT_PAGE, model);
		model.addAttribute("action", SystemConstant.EDIT);
		try {
			model.addAttribute("componentDTO", componentService.getDetails(id));
			model.addAttribute("activeTitleList", titleService.getTitleListByStatus(1));
			return "/admin/component/create-or-edit";
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
	}

	@PostMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String handleEditFeature(@ModelAttribute("componentDTO") ComponentDTO dto, RedirectAttributes redirectModel,
			Model model) {
		return save(SystemConstant.EDIT, dto, redirectModel, model);
	}

	@GetMapping(value = "/details")
	public String showDetailsPage(@RequestParam(value = "id", required = false) String id, Model model) {
		setViewTitleAndFavicon(SystemConstant.COMPONENT_TITLE_DETAILS_PAGE, model);
		try {
			model.addAttribute("componentDTO", componentService.getDetails(id));
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
		return "/admin/component/details";
	}

	private String save(String action, ComponentDTO dto, RedirectAttributes redirectModel, Model model) {
		if (componentService.indexIsInvalid(dto, dto.getCIndex())) {
			model.addAttribute("cIndexInvalid", "This index was existed");
			return commonData(action, dto, model);
		}
		if (componentService.save(dto) != null) {
			if (action.equals(SystemConstant.CREATE)) {
				notification(SystemConstant.SUCCESSFUL_INSERT, SystemConstant.SUCCESS_ALERT, redirectModel);
			} else {
				notification(SystemConstant.UPDATE_SUCCESSFUL, SystemConstant.SUCCESS_ALERT, redirectModel);
			}
		} else {
			model.addAttribute("duplicateSubtitle", SystemConstant.DUPLICATE_ERR);
			return commonData(action, dto, model);
		}
		return "redirect:/admin/component";
	}

	private String commonData(String action, ComponentDTO dto, Model model) {
		String viewTitle = null;
		if (action.equals(SystemConstant.CREATE)) {
			viewTitle = SystemConstant.COMPONENT_TITLE_CREATE_PAGE;
		} else {
			viewTitle = SystemConstant.COMPONENT_TITLE_EDIT_PAGE;
		}
		setViewTitleAndFavicon(viewTitle, model);
		model.addAttribute("componentDTO", dto);
		model.addAttribute("action", action);
		model.addAttribute("activeTitleList", titleService.getTitleListByStatus(1));
		return "/admin/component/create-or-edit";
	}

	@GetMapping(value = "/change-index-position")
	public String showChangeTheIndexPositionPage(Model model) {
		return setUpChangeTheIndexPositionPage(new IdParameterDTO(), model);
	}

	@PostMapping(value = "/change-index-position")
	public String handleChangeTheIndexPositionFeature(@Valid @ModelAttribute("componentId") IdParameterDTO dto,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		if (bindingResult.hasErrors()) {
			return setUpChangeTheIndexPositionPage(dto, model);
		}
		if (componentService.changeIndexPosition(dto)) {
			notification(SystemConstant.CHANGE_INDEX_SUCCESSFULLY, SystemConstant.SUCCESS_ALERT, redirectModel);
		} else {
			notification(SystemConstant.CHANGE_INDEX_UNSUCCESSFULLY, SystemConstant.SUCCESS_ALERT, redirectModel);
		}
		return "redirect:/admin/component";
	}

	private String setUpChangeTheIndexPositionPage(IdParameterDTO dto, Model model) {
		setViewTitleAndFavicon(SystemConstant.CHANGE_INDEX_POSITION_PAGE, model);
		model.addAttribute("componentId", dto);
		model.addAttribute("activeComponentList", componentService.getComponentListByStatus(1));
		return "/admin/component/change-index-position";
	}
}
