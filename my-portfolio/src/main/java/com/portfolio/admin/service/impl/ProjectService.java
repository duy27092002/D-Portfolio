package com.portfolio.admin.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.admin.dto.ProjectDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.entity.LanguageAndTechnology;
import com.portfolio.admin.entity.Project;
import com.portfolio.admin.repository.LanguageAndTechnologyRepository;
import com.portfolio.admin.repository.ProjectRepository;
import com.portfolio.admin.service.IProjectService;

@Service
@Transactional
public class ProjectService implements IProjectService {
	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private LanguageAndTechnologyRepository ltRepo;

	@Override
	public List<ProjectDTO> getAllList() {
		return null;
	}

	@Override
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) {
		try {
			return responseDataTableDTO.getList(projectRepo, new ProjectDTO().getClass(),
					responseDataTableDTO.getKeyword());
		} catch (Exception e) {
			System.out.println("Error at getList method in ProjectService");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ProjectDTO save(ProjectDTO dto) {
		String getProjectId = dto.getId();
		Project projectEntity = null;
		if (getProjectId.isBlank() || getProjectId == null) {
			if (wasExisted(dto.getName())) {
				return null;
			}
			projectEntity = new Project();
		} else {
			projectEntity = projectRepo.findById(getProjectId).get();
			if (!dto.getName().equalsIgnoreCase(projectEntity.getName())) {
				if (wasExisted(dto.getName())) {
					return null;
				}
			}
		}
		// convert string to date type
		dto.setStartDate(convertStrToDate(dto.getStartDateStr()));
		String getEndDateStrInput = dto.getEndDateStr();
		String getEndDateStr = null;
		if (getEndDateStrInput.contains("Present")) {
			getEndDateStr = null;
		} else {
			getEndDateStr = dto.getEndDateStr().split(",")[1];
		}
		dto.setEndDate(convertStrToDate(getEndDateStr));
		// end convert

		BeanUtils.copyProperties(dto, projectEntity);
		String[] getLTIdList = dto.getLtIdList();
		for (String ltId : getLTIdList) {
			LanguageAndTechnology getLTById = ltRepo.findById(ltId).get();
			projectEntity.addLanguagesAndTechnologies(getLTById);
		}
		BeanUtils.copyProperties(projectRepo.save(projectEntity), dto);
		return dto;
	}

	@Override
	public ProjectDTO getDetails(String id) {
		Project getProjectEntityById = projectRepo.findById(id).get();
		ProjectDTO dto = new ProjectDTO();
		BeanUtils.copyProperties(getProjectEntityById, dto);
		return dto;
	}

	@Override
	public boolean checkDate(String startDateStr, String endDateStr) {
		// endDateStr parameter has 3 case:
		// case 1: endDateStr = "Present"
		// case 2: endDateStr = "on,2022-01-01" (2022-01-01 can change)
		// case 3: endDateStr = "on," (when user select "Another option" but do not
		// select time)
		Date startDate = convertStrToDate(startDateStr);
		if (startDate == null) {
			return false;
		}
		if (endDateStr.contains("Present")) {
			endDateStr = endDateStr.split(",")[0];
		} else {
			if (endDateStr.equalsIgnoreCase("on,")) {
				return false;
			}
			endDateStr = endDateStr.split(",")[1];
		}
		if ("Present".equalsIgnoreCase(endDateStr)) {
			endDateStr = null;
		}
		Date endDate = convertStrToDate(endDateStr);
		if (endDate == null || startDate.compareTo(endDate) <= 0) {
			return true;
		}
		return false;
	}

	private boolean wasExisted(String projectName) {
		return projectRepo.findByName(projectName) != null ? true : false;
	}

	private Date convertStrToDate(String strDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date result = df.parse(strDate);
			return result;
		} catch (Exception e) {
			System.out.println("Error when convert string time to date");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProjectDTO> getProjectListByStatus(int status) {
		List<Project> entityList = projectRepo.findByStatusOrderByStartDateDesc(status);
		List<ProjectDTO> resultList = new ArrayList<>();
		for (Project entity : entityList) {
			ProjectDTO dto = new ProjectDTO();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}
}
