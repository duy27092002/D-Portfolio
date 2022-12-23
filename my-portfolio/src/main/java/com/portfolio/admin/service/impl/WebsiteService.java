package com.portfolio.admin.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.admin.dto.WebsiteDTO;
import com.portfolio.admin.entity.Website;
import com.portfolio.admin.repository.WebsiteRepository;
import com.portfolio.admin.service.IWebsiteService;
import com.portfolio.common.utils.SaveFileLocal;

@Service
@Transactional
public class WebsiteService implements IWebsiteService {
	@Autowired
	private WebsiteRepository websiteRepo;
	
	@Autowired
	private SaveFileLocal saveFileLocal;

	@Override
	public List<WebsiteDTO> getAllList() {
		return null;
	}

	@Override
	public WebsiteDTO save(WebsiteDTO dto) {
		try {
			Website getOldWebInfo = websiteRepo.findById(dto.getId()).get();
			
			if (dto.getFaviconFile().getOriginalFilename().isEmpty()) {
				dto.setFavicon(getOldWebInfo.getFavicon());
			} else {
				dto.setFavicon(saveFileLocal.saveFile(dto.getFaviconFile()));
			}
			
			if (dto.getLogoFile().getOriginalFilename().isEmpty()) {
				dto.setLogo(getOldWebInfo.getLogo());
			} else {
				dto.setLogo(saveFileLocal.saveFile(dto.getLogoFile()));
			}
			
			BeanUtils.copyProperties(dto, getOldWebInfo);
			BeanUtils.copyProperties(websiteRepo.save(getOldWebInfo), dto);
		} catch (IOException e) {
			System.out.println("Error update website info");
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public WebsiteDTO getDetails(String id) {
		Website getWebsiteEntity = websiteRepo.findById(id).get();
		WebsiteDTO websiteInfo = new WebsiteDTO();
		BeanUtils.copyProperties(getWebsiteEntity, websiteInfo);
		return websiteInfo;
	}

}
