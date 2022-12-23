package com.portfolio.admin.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.admin.dto.ComponentDTO;
import com.portfolio.admin.dto.IdParameterDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.entity.Component;
import com.portfolio.admin.entity.Title;
import com.portfolio.admin.repository.ComponentRepository;
import com.portfolio.admin.repository.TitleRepository;
import com.portfolio.admin.service.IComponentService;
import com.portfolio.common.constant.SystemConstant;
import com.portfolio.common.utils.SaveFileLocal;

@Service
@Transactional
public class ComponentService implements IComponentService {
	@Autowired
	private ComponentRepository componentRepo;

	@Autowired
	private TitleRepository titleRepo;

	@Autowired
	private SaveFileLocal saveFileLocal;

	@Override
	public List<ComponentDTO> getAllList() {
		return null;
	}

	@Override
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) {
		try {
			return responseDataTableDTO.getList(componentRepo, new ComponentDTO().getClass(),
					responseDataTableDTO.getKeyword());
		} catch (Exception e) {
			System.out.println("Error at getList method in ComponentService");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ComponentDTO save(ComponentDTO dto) {
		String getComponentId = dto.getId();
		Component componentEntity = null;
		if (getComponentId.isBlank() || getComponentId == null) {
			if (wasExisted(dto.getSubTitle())) {
				return null;
			}
			componentEntity = new Component();
			handleSaveImageFeature(SystemConstant.CREATE, dto);
		} else {
			componentEntity = componentRepo.findById(getComponentId).get();
			if (!dto.getSubTitle().equalsIgnoreCase(componentEntity.getSubTitle())) {
				if (wasExisted(dto.getSubTitle())) {
					return null;
				}
			}
			handleSaveImageFeature(SystemConstant.EDIT, dto);
		}
		BeanUtils.copyProperties(dto, componentEntity);
		Title getTitleEntityById = titleRepo.findById(dto.getTitleId()).get();
		componentEntity.setTitle(getTitleEntityById);
		BeanUtils.copyProperties(componentRepo.save(componentEntity), dto);
		return dto;
	}

	@Override
	public ComponentDTO getDetails(String id) {
		Component getComponentEntityById = componentRepo.findById(id).get();
		ComponentDTO dto = new ComponentDTO();
		BeanUtils.copyProperties(getComponentEntityById, dto);
		return dto;
	}

	@Override
	public boolean indexIsInvalid(ComponentDTO dto, int cIndex) {
		String getComponentId = dto.getId();
		if (getComponentId.isBlank() || getComponentId == null) {
			return componentRepo.findBycIndex(cIndex) != null ? true : false;
		} else {
			Component getOldComponentInfo = componentRepo.findById(getComponentId).get();
			if (dto.getCIndex() != getOldComponentInfo.getCIndex()) {
				return componentRepo.findBycIndex(cIndex) != null ? true : false;
			}
		}
		return false;
	}

	private boolean wasExisted(String subTitle) {
		return componentRepo.findBySubTitle(subTitle) != null ? true : false;
	}

	private void handleSaveImageFeature(String action, ComponentDTO dto) {
		String imageName = null;
		if (action.equalsIgnoreCase(SystemConstant.CREATE)) {
			imageName = SystemConstant.IMAGE_DEFAULT;
		} else {
			imageName = componentRepo.findById(dto.getId()).get().getImage();
		}
		if (dto.getImageFile().getOriginalFilename().isEmpty()) {
			dto.setImage(imageName);
		} else {
			try {
				dto.setImage(saveFileLocal.saveFile(dto.getImageFile()));
			} catch (IOException e) {
				System.out.println("Error when save image in ComponentService");
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<ComponentDTO> getComponentListByStatus(int status) {
		List<Component> getComponentListByStatus = componentRepo.findByStatus(status);
		List<ComponentDTO> resultList = new ArrayList<>();
		for (Component item : getComponentListByStatus) {
			ComponentDTO dto = new ComponentDTO();
			BeanUtils.copyProperties(item, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public boolean changeIndexPosition(IdParameterDTO dto) {
		try {
			Component getFirstComponentById = componentRepo.findById(dto.getFirstId()).get();
			int getFirstComponentIndex = getFirstComponentById.getCIndex();
			Component getSecondComponentById = componentRepo.findById(dto.getSecondId()).get();
			int getSecondComponentIndex = getSecondComponentById.getCIndex();
			getFirstComponentById.setCIndex(getSecondComponentIndex);
			componentRepo.save(getFirstComponentById);
			getSecondComponentById.setCIndex(getFirstComponentIndex);
			componentRepo.save(getSecondComponentById);
			return true;
		} catch (Exception e) {
			System.out.println("Error when change component index position");
		}
		return false;
	}
}
