package com.portfolio.admin.service;

import java.util.List;

import com.portfolio.admin.dto.ProjectDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;

public interface IProjectService extends IBaseService<ProjectDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO);
	
	boolean checkDate(String startDateStr, String endDateStr);
	
	List<ProjectDTO> getProjectListByStatus(int status);
}
