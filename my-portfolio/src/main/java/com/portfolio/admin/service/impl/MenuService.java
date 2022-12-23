package com.portfolio.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.admin.dto.MenuDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.entity.Menu;
import com.portfolio.admin.repository.MenuRepository;
import com.portfolio.admin.service.IMenuService;

@Service
@Transactional
public class MenuService implements IMenuService {
	@Autowired
	private MenuRepository menuRepo;

	@Override
	public List<MenuDTO> getAllList() {
		return null;
	}

	@Override
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTable) {
		try {
			return responseDataTable.getList(menuRepo, new MenuDTO().getClass(), responseDataTable.getKeyword());
		} catch (Exception e) {
			System.out.println("Error at getList method in MenuService");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MenuDTO save(MenuDTO dto) {
		String getMenuId = dto.getId();
		Menu menuEntity = null;
		if (getMenuId.isBlank() || getMenuId == null) {
			if (wasExisted(dto.getUrlCode())) {
				return null;
			}
			menuEntity = new Menu();
		} else {
			menuEntity = menuRepo.findById(getMenuId).get();
			if (!dto.getName().equalsIgnoreCase(getDetails(getMenuId).getName())) {
				if (wasExisted(dto.getUrlCode())) {
					return null;
				}
			}
		}
		BeanUtils.copyProperties(dto, menuEntity);
		BeanUtils.copyProperties(menuRepo.save(menuEntity), dto);
		return dto;
	}

	@Override
	public MenuDTO getDetails(String id) {
		Menu getMenuEntityById = menuRepo.findById(id).get();
		MenuDTO menuDTO = new MenuDTO();
		BeanUtils.copyProperties(getMenuEntityById, menuDTO);
		return menuDTO;
	}

	private boolean wasExisted(String urlCode) {
		return menuRepo.findByUrlCode(urlCode) != null;
	}

	@Override
	public List<MenuDTO> getMenuListByStatus(int status) {
		List<Menu> entityList = menuRepo.findByStatus(status);
		List<MenuDTO> resultList = new ArrayList<>();
		for (Menu entity : entityList) {
			MenuDTO dto = new MenuDTO();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}
}
