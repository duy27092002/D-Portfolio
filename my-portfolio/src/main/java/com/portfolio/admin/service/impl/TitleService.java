package com.portfolio.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.dto.TitleDTO;
import com.portfolio.admin.dto.IdParameterDTO;
import com.portfolio.admin.entity.Title;
import com.portfolio.admin.repository.TitleRepository;
import com.portfolio.admin.service.ITitleService;

@Service
@Transactional
public class TitleService implements ITitleService {
	@Autowired
	private TitleRepository titleRepo;

	@Override
	public List<TitleDTO> getAllList() {
		return null;
	}

	@Override
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) {
		try {
			return responseDataTableDTO.getList(titleRepo, new TitleDTO().getClass(),
					responseDataTableDTO.getKeyword());
		} catch (Exception e) {
			System.out.println("Error when getList method in TitleService");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TitleDTO save(TitleDTO dto) {
		String getTitleId = dto.getId();
		Title titleEntity = null;
		if (getTitleId.isBlank() || getTitleId == null) {
			if (wasExisted(dto.getName())) {
				return null;
			}
			titleEntity = new Title();
		} else {
			titleEntity = titleRepo.findById(getTitleId).get();
			if (!dto.getName().equalsIgnoreCase(titleEntity.getName())) {
				if (wasExisted(dto.getName())) {
					return null;
				}
			}
		}
		BeanUtils.copyProperties(dto, titleEntity);
		BeanUtils.copyProperties(titleRepo.save(titleEntity), dto);
		return dto;
	}

	@Override
	public TitleDTO getDetails(String id) {
		Title getTitleEntityById = titleRepo.findById(id).get();
		TitleDTO dto = new TitleDTO();
		BeanUtils.copyProperties(getTitleEntityById, dto);
		return dto;
	}

	private boolean wasExisted(String name) {
		return titleRepo.findByName(name) != null ? true : false;
	}

	@Override
	public List<TitleDTO> getTitleListByStatus(int status) {
		List<Title> titleEntityListByStatus = titleRepo.findByStatus(status);
		List<TitleDTO> resultList = new ArrayList<>();
		for (Title item : titleEntityListByStatus) {
			TitleDTO dto = new TitleDTO();
			BeanUtils.copyProperties(item, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public boolean titleIndexWasExisted(TitleDTO titleDTO, int index) {
		String getTitleId = titleDTO.getId();
		if (getTitleId.isBlank() || getTitleId == null) {
			return titleRepo.findByTitleIndex(index) != null ? true : false;
		} else {
			Title getOldTitleInfo = titleRepo.findById(getTitleId).get();
			if (titleDTO.getTitleIndex() != getOldTitleInfo.getTitleIndex()) {
				return titleRepo.findByTitleIndex(index) != null ? true : false;
			}
		}
		return false;
	}

	@Override
	public boolean changeIndexPosition(IdParameterDTO dto) {
		try {
			Title getFirstTitleById = titleRepo.findById(dto.getFirstId()).get();
			int getFirstTitleIndex = getFirstTitleById.getTitleIndex();
			Title getSecondTitleById = titleRepo.findById(dto.getSecondId()).get();
			int getSecondTitleIndex = getSecondTitleById.getTitleIndex();
			getFirstTitleById.setTitleIndex(getSecondTitleIndex);
			getSecondTitleById.setTitleIndex(getFirstTitleIndex);
			titleRepo.save(getFirstTitleById);
			titleRepo.save(getSecondTitleById);
			return true;
		} catch (Exception e) {
			System.out.println("Error when change the index position at TitleSerive");
			e.printStackTrace();
		}
		return false;
	}
}
