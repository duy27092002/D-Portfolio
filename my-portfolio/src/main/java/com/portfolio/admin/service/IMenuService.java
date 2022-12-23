package com.portfolio.admin.service;

import java.util.List;

import com.portfolio.admin.dto.MenuDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;

public interface IMenuService extends IBaseService<MenuDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTable);
	
	List<MenuDTO> getMenuListByStatus(int status);
}
