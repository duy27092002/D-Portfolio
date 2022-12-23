package com.portfolio.admin.service;

import java.util.List;

import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.dto.TitleDTO;
import com.portfolio.admin.dto.IdParameterDTO;

public interface ITitleService extends IBaseService<TitleDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO);
	
	List<TitleDTO> getTitleListByStatus(int status);
	
	boolean titleIndexWasExisted(TitleDTO titleDTO, int index);
	
	boolean changeIndexPosition(IdParameterDTO dto);
}
