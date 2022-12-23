package com.portfolio.admin.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.admin.dto.ResumeDTO;
import com.portfolio.admin.entity.Resume;
import com.portfolio.admin.repository.ResumeRepository;
import com.portfolio.admin.service.IResumeService;

@Service
@Transactional
public class ResumeService implements IResumeService {
	@Autowired
	private ResumeRepository resumeRepo;

	@Override
	public List<ResumeDTO> getAllList() {
		return null;
	}

	@Override
	public ResumeDTO save(ResumeDTO dto) {
		Resume getOldResumeInfo = resumeRepo.findById(dto.getId()).get();
		BeanUtils.copyProperties(dto, getOldResumeInfo);
		BeanUtils.copyProperties(resumeRepo.save(getOldResumeInfo), dto);
		return dto;
	}

	@Override
	public ResumeDTO getDetails(String id) {
		Resume getResumeEntity = resumeRepo.findById(id).get();
		ResumeDTO dto = new ResumeDTO();
		BeanUtils.copyProperties(getResumeEntity, dto);
		return dto;
	}

}
