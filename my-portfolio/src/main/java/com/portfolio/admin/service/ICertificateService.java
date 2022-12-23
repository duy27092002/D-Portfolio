package com.portfolio.admin.service;

import java.util.List;

import com.portfolio.admin.dto.CertificateDTO;
import com.portfolio.admin.dto.ResponseDataTableDTO;

public interface ICertificateService extends IBaseService<CertificateDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO);
	
	List<CertificateDTO> getCertificateListByStatus(int status);
}
