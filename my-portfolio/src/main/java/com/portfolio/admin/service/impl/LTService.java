package com.portfolio.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.admin.dto.LanguageAndTechnologyDTO;
import com.portfolio.admin.dto.ProjectDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.entity.LanguageAndTechnology;
import com.portfolio.admin.repository.LanguageAndTechnologyRepository;
import com.portfolio.admin.service.ILTService;

@Service
@Transactional
public class LTService implements ILTService {
	@Autowired
	private LanguageAndTechnologyRepository ltRepo;

	@Override
	public List<LanguageAndTechnologyDTO> getAllList() {
		return null;
	}

	@Override
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) {
		try {
			return responseDataTableDTO.getList(ltRepo, new LanguageAndTechnologyDTO().getClass(),
					responseDataTableDTO.getKeyword());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LanguageAndTechnologyDTO save(LanguageAndTechnologyDTO dto) {
		String getLTId = dto.getId();
		LanguageAndTechnology ltEntity = null;
		if (getLTId.isBlank() || getLTId == null) {
			if (wasExisted(dto.getName())) {
				return null;
			}
			ltEntity = new LanguageAndTechnology();
		} else {
			ltEntity = ltRepo.findById(getLTId).get();
			String getInputName = dto.getName();
			if (!getInputName.equalsIgnoreCase(ltEntity.getName())) {
				if (wasExisted(getInputName)) {
					return null;
				}
			}
		}
		BeanUtils.copyProperties(dto, ltEntity);
		BeanUtils.copyProperties(ltRepo.save(ltEntity), dto);
		return dto;
	}

	@Override
	public LanguageAndTechnologyDTO getDetails(String id) {
		LanguageAndTechnology ltEntityDetails = ltRepo.findById(id).get();
		LanguageAndTechnologyDTO ltDTODetails = new LanguageAndTechnologyDTO();
		BeanUtils.copyProperties(ltEntityDetails, ltDTODetails);
		return ltDTODetails;
	}

	private boolean wasExisted(String ltName) {
		return ltRepo.findByName(ltName) != null ? true : false;
	}

	@Override
	public List<LanguageAndTechnologyDTO> getListByStatus(int status) {
		List<LanguageAndTechnology> getLTEntityListByStatus = ltRepo.findByStatus(status);
		List<LanguageAndTechnologyDTO> resultList = new ArrayList<>();
		for (LanguageAndTechnology entity : getLTEntityListByStatus) {
			LanguageAndTechnologyDTO dto = new LanguageAndTechnologyDTO();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public List<String> getLTIdListByProject(ProjectDTO projectDTO) {
		List<LanguageAndTechnology> getLTListByProject = projectDTO.getLanguagesAndTechnologies();
		List<String> resultList = new ArrayList<>();
		for (LanguageAndTechnology item : getLTListByProject) {
			resultList.add(item.getId());
		}
		return resultList;
	}
}
