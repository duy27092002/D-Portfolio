package com.portfolio.admin.service;

import java.util.List;

import com.portfolio.admin.dto.LanguageAndTechnologyDTO;
import com.portfolio.admin.dto.ProjectDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;

public interface ILTService extends IBaseService<LanguageAndTechnologyDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO);
	
	List<LanguageAndTechnologyDTO> getListByStatus(int status);
	
	List<String> getLTIdListByProject(ProjectDTO projectDTO);
}
