package com.portfolio.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.portfolio.admin.dto.AboutMeDTO;
import com.portfolio.admin.dto.CertificateDTO;
import com.portfolio.admin.dto.LanguageAndTechnologyDTO;
import com.portfolio.admin.dto.MenuDTO;
import com.portfolio.admin.dto.ProjectDTO;
import com.portfolio.admin.dto.WebsiteDTO;
import com.portfolio.admin.service.IAboutMeService;
import com.portfolio.admin.service.ICertificateService;
import com.portfolio.admin.service.ILTService;
import com.portfolio.admin.service.IMenuService;
import com.portfolio.admin.service.IProjectService;
import com.portfolio.admin.service.IResumeService;
import com.portfolio.admin.service.IWebsiteService;
import com.portfolio.common.constant.SystemConstant;

@Controller
public class PortfolioController {
	@Autowired
	private IAboutMeService aboutMeService;

	@Autowired
	private IWebsiteService websiteService;

	@Autowired
	private IResumeService resumeService;

	@Autowired
	private IMenuService menuService;

	@Autowired
	private ILTService ltService;

	@Autowired
	private ICertificateService certificateService;

	@Autowired
	private IProjectService projectService;

	@GetMapping(value = "/my-portfolio")
	public String viewPortfolioPage(Model model) {
		AboutMeDTO getAboutMeDetails = aboutMeService.getDetails(SystemConstant.ABOUT_ME_ID);
		model.addAttribute("aboutMe", getAboutMeDetails);
		WebsiteDTO getWebsiteInfo = websiteService.getDetails(SystemConstant.WEBSITE_ID);
		model.addAttribute("websiteInfo", getWebsiteInfo);
		model.addAttribute("resumePDFLink", resumeService.getDetails(SystemConstant.RESUME_ID).getPdfLinkDrive());
		List<MenuDTO> getActiveMenuList = menuService.getMenuListByStatus(1);
		model.addAttribute("activeMenuList", getActiveMenuList);
		List<LanguageAndTechnologyDTO> getActiveLTList = ltService.getListByStatus(1);
		model.addAttribute("activeLTList", getActiveLTList);
		List<CertificateDTO> activeCertificateList = certificateService.getCertificateListByStatus(1);
		model.addAttribute("activeCertificateList", activeCertificateList);
		List<ProjectDTO> getActiveProjectList = projectService.getProjectListByStatus(1);
		model.addAttribute("activeProjectList", getActiveProjectList);
		return "/web/index";
	}
}
