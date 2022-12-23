package com.portfolio.admin.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.portfolio.admin.dto.ProjectDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.paging.PagingParam;
import com.portfolio.admin.service.ILTService;
import com.portfolio.admin.service.IProjectService;
import com.portfolio.common.constant.SystemConstant;

@Controller
@RequestMapping(value = "/admin/personal-project")
public class ProjectController extends BaseController {
	@Autowired
	private IProjectService projectService;

	@Autowired
	private ILTService ltService;

	@GetMapping
	public String showListPage(
			@PagingParam(path = "project", orderBy = "startDate", orderType = "desc") ResponseDataTableDTO responseDataTableDTO,
			Model model) {
		try {
			setViewTitleAndFavicon(SystemConstant.PROJECT_TITLE_LIST_PAGE, model);
			ResponseDataTableDTO resultList = projectService.getList(responseDataTableDTO);
			model.addAttribute("totalItemsFound", resultList.getTotalOfItem());
			model.addAttribute("resultList", resultList);
			String getKeywordParam = responseDataTableDTO.getKeyword();
			if (getKeywordParam != null) {
				model.addAttribute("keyword", getKeywordParam);
			}
			model.addAttribute("orderType",
					responseDataTableDTO.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc");
			return "/admin/project/list";
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
	}

	@GetMapping(value = "/create")
	public String showCreatePage(Model model) {
		setViewTitleAndFavicon(SystemConstant.PROJECT_TITLE_CREATE_PAGE, model);
		model.addAttribute("action", SystemConstant.CREATE);
		model.addAttribute("projectDTO", new ProjectDTO());
		model.addAttribute("activeLTList", ltService.getListByStatus(1));
		return "/admin/project/create-or-edit";
	}

	@PostMapping(value = "/create")
	public String handleCreateFeature(@Valid @ModelAttribute("projectDTO") ProjectDTO dto, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save(SystemConstant.CREATE, dto, bindingResult, redirectModel, model);
	}

	@GetMapping(value = "/edit")
	public String showEditPage(@RequestParam(value = "id", required = false) String id, Model model) {
		setViewTitleAndFavicon(SystemConstant.PROJECT_TITLE_EDIT_PAGE, model);
		model.addAttribute("action", SystemConstant.EDIT);
		try {
			ProjectDTO getDetails = projectService.getDetails(id);
			model.addAttribute("projectDTO", getDetails);
			model.addAttribute("activeLTList", ltService.getListByStatus(1));
			// get language & technology id by project (by project id)
			model.addAttribute("ltIdListByProject", ltService.getLTIdListByProject(getDetails));
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
		return "/admin/project/create-or-edit";
	}

	@PostMapping(value = "/edit")
	public String handleEditFeature(@Valid @ModelAttribute("projectDTO") ProjectDTO dto, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		return save(SystemConstant.EDIT, dto, bindingResult, redirectModel, model);
	}

	@GetMapping(value = "/details")
	public String showDetailsPage(@RequestParam(value = "id", required = false) String id, Model model) {
		setViewTitleAndFavicon(SystemConstant.PROJECT_TITLE_DETAILS_PAGE, model);
		try {
			model.addAttribute("projectDetails", projectService.getDetails(id));
		} catch (Exception e) {
			return "rediect:/admin/error";
		}
		return "/admin/project/details";
	}

	private String save(String action, ProjectDTO dto, BindingResult bindingResult, RedirectAttributes redirectModel,
			Model model) {
		if (bindingResult.hasErrors()) {
			return checkDTOValidation(action, dto, model);
		}
		if (projectService.checkDate(dto.getStartDateStr(), dto.getEndDateStr())) {
			if (projectService.save(dto) != null) {
				if (action.equals(SystemConstant.CREATE)) {
					notification(SystemConstant.SUCCESSFUL_INSERT, SystemConstant.SUCCESS_ALERT, redirectModel);
				} else {
					notification(SystemConstant.UPDATE_SUCCESSFUL, SystemConstant.SUCCESS_ALERT, redirectModel);
				}
			} else {
				model.addAttribute("duplicateErr", SystemConstant.DUPLICATE_ERR);
				return checkDTOValidation(action, dto, model);
			}
		} else {
			model.addAttribute("invalidDate", "The end date cannot empty or cannot occur before the start date");
			return checkDTOValidation(action, dto, model);
		}
		return "redirect:/admin/personal-project";
	}

	private String checkDTOValidation(String action, ProjectDTO dto, Model model) {
		String viewTitle = null;
		if (action.equals(SystemConstant.CREATE)) {
			viewTitle = SystemConstant.PROJECT_TITLE_CREATE_PAGE;
		} else {
			viewTitle = SystemConstant.PROJECT_TITLE_EDIT_PAGE;
			List<String> getLTIdListByProject = new ArrayList<>();
			String[] getLTIdArray = dto.getLtIdList();
			for (int i = 0; i < getLTIdArray.length; i++) {
				getLTIdListByProject.add(getLTIdArray[i]);
			}
			model.addAttribute("ltIdListByProject", getLTIdListByProject);
		}
		setViewTitleAndFavicon(viewTitle, model);
		model.addAttribute("projectDTO", dto);
		model.addAttribute("action", action);
		model.addAttribute("activeLTList", ltService.getListByStatus(1));
		return "/admin/project/create-or-edit";
	}
}
