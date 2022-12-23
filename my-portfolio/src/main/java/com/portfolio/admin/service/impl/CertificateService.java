package com.portfolio.admin.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.admin.dto.CertificateDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;
import com.portfolio.admin.entity.Certificate;
import com.portfolio.admin.repository.CertificateRepository;
import com.portfolio.admin.service.ICertificateService;

@Service
@Transactional
public class CertificateService implements ICertificateService {
	@Autowired
	private CertificateRepository certificateRepo;

	@Override
	public List<CertificateDTO> getAllList() {
		return null;
	}

	@Override
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) {
		try {
			return responseDataTableDTO.getList(certificateRepo, new CertificateDTO().getClass(),
					responseDataTableDTO.getKeyword());
		} catch (Exception e) {
			System.out.println("Error at getList method in CertificateService");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CertificateDTO save(CertificateDTO dto) {
		String getCertificateId = dto.getId();
		Certificate certificateEntity = null;
		if (getCertificateId.isBlank() || getCertificateId == null) {
			if (wasExisted(dto.getName())) {
				return null;
			}
			certificateEntity = new Certificate();
		} else {
			certificateEntity = certificateRepo.findById(getCertificateId).get();
			String getInputCertificateName = dto.getName();
			if (!getInputCertificateName.equalsIgnoreCase(certificateEntity.getName())) {
				if (wasExisted(getInputCertificateName)) {
					return null;
				}
			}
		}
		BeanUtils.copyProperties(dto, certificateEntity);
		// convert string to date
		String getTimeStr = dto.getTimeStr();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date timeDate = null;
		try {
			timeDate = df.parse(getTimeStr);
		} catch (ParseException e) {
			System.out.println("Error when convert string time to date");
			e.printStackTrace();
		}
		// end convert
		certificateEntity.setTime(timeDate);
		BeanUtils.copyProperties(certificateRepo.save(certificateEntity), dto);
		return dto;
	}

	@Override
	public CertificateDTO getDetails(String id) {
		Certificate getCertificateEntityById = certificateRepo.findById(id).get();
		CertificateDTO dto = new CertificateDTO();
		BeanUtils.copyProperties(getCertificateEntityById, dto);
		return dto;
	}

	private boolean wasExisted(String name) {
		return certificateRepo.findByName(name) != null ? true : false;
	}

	@Override
	public List<CertificateDTO> getCertificateListByStatus(int status) {
		List<Certificate> entityList = certificateRepo.findByStatus(status);
		List<CertificateDTO> resultList = new ArrayList<>();
		for (Certificate entity : entityList) {
			CertificateDTO dto = new CertificateDTO();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}
}
