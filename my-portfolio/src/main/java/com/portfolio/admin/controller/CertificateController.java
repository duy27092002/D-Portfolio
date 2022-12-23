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

import com.portfolio.admin.dto.CertificateDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.paging.PagingParam;
import com.portfolio.admin.service.ICertificateService;
import com.portfolio.common.constant.SystemConstant;

@Controller
@RequestMapping(value = "/admin/certificate")
public class CertificateController extends BaseController {
	@Autowired
	private ICertificateService certificateService;

	@GetMapping
	public String showListPage(
			@PagingParam(path = "certificate", orderBy = "time", orderType = "desc") ResponseDataTableDTO responseDataTableDTO,
			Model model) {
		try {
			setViewTitleAndFavicon(SystemConstant.CERTIFICATE_TITLE_LIST_PAGE, model);
			ResponseDataTableDTO resultList = certificateService.getList(responseDataTableDTO);
			model.addAttribute("totalItemsFound", resultList.getTotalOfItem());
			model.addAttribute("resultList", resultList);
			String getKeywordParam = responseDataTableDTO.getKeyword();
			if (getKeywordParam != null) {
				model.addAttribute("keyword", getKeywordParam);
			}
			model.addAttribute("orderType",
					responseDataTableDTO.getOrderType().equalsIgnoreCase("asc") ? "desc" : "asc");
			return "/admin/certificate/list";
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
	}

	@GetMapping(value = "/create")
	public String showCreatePage(Model model) {
		setViewTitleAndFavicon(SystemConstant.CERTIFICATE_TITLE_CREATE_PAGE, model);
		model.addAttribute("action", SystemConstant.CREATE);
		model.addAttribute("certificateDTO", new CertificateDTO());
		return "/admin/certificate/create-or-edit";
	}

	@PostMapping(value = "/create")
	public String handleCreateFeature(@Valid @ModelAttribute("certificateDTO") CertificateDTO dto,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		return save(SystemConstant.CREATE, dto, bindingResult, redirectModel, model);
	}

	@GetMapping(value = "/edit")
	public String showEditPage(@RequestParam(value = "id", required = false) String id, Model model) {
		setViewTitleAndFavicon(SystemConstant.CERTIFICATE_TITLE_EDIT_PAGE, model);
		model.addAttribute("action", SystemConstant.EDIT);
		try {
			model.addAttribute("certificateDTO", certificateService.getDetails(id));
		} catch (Exception e) {
			return "redirect:/admin/error";
		}
		return "/admin/certificate/create-or-edit";
	}

	@PostMapping(value = "/edit")
	public String handleEditFeature(@Valid @ModelAttribute("certificateDTO") CertificateDTO dto,
			BindingResult bindingResult, RedirectAttributes redirectModel, Model model) {
		return save(SystemConstant.EDIT, dto, bindingResult, redirectModel, model);
	}

	private String save(String action, CertificateDTO dto, BindingResult bindingResult,
			RedirectAttributes redirectModel, Model model) {
		if (bindingResult.hasErrors()) {
			return checkDTOValidation(action, dto, model);
		}
		if (certificateService.save(dto) != null) {
			if (action.equals(SystemConstant.CREATE)) {
				notification(SystemConstant.SUCCESSFUL_INSERT, SystemConstant.SUCCESS_ALERT, redirectModel);
			} else {
				notification(SystemConstant.UPDATE_SUCCESSFUL, SystemConstant.SUCCESS_ALERT, redirectModel);
			}
		} else {
			model.addAttribute("duplicateErr", SystemConstant.DUPLICATE_ERR);
			return checkDTOValidation(action, dto, model);
		}
		return "redirect:/admin/certificate";
	}

	private String checkDTOValidation(String action, CertificateDTO dto, Model model) {
		String viewTitle = null;
		if (action.equals(SystemConstant.CREATE)) {
			viewTitle = SystemConstant.CERTIFICATE_TITLE_CREATE_PAGE;
		} else {
			viewTitle = SystemConstant.CERTIFICATE_TITLE_EDIT_PAGE;
		}
		setViewTitleAndFavicon(viewTitle, model);
		model.addAttribute("certificateDTO", dto);
		model.addAttribute("action", action);
		return "/admin/certificate/create-or-edit";
	}
}
