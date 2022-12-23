package com.portfolio.admin.service;

import java.util.List;

import com.portfolio.admin.dto.ComponentDTO;
import com.portfolio.admin.dto.IdParameterDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;

public interface IComponentService extends IBaseService<ComponentDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO);
	
	public boolean indexIsInvalid(ComponentDTO dto, int cIndex);
	
	public List<ComponentDTO> getComponentListByStatus(int status);
	
	public boolean changeIndexPosition(IdParameterDTO dto);
}
