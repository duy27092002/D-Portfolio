package com.portfolio.admin.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.admin.dto.AboutMeDTO;
import com.portfolio.admin.dto.ChangePasswordDTO;
import com.portfolio.admin.entity.AboutMe;
import com.portfolio.admin.repository.AboutMeRepository;
import com.portfolio.admin.service.IAboutMeService;
import com.portfolio.common.constant.SystemConstant;
import com.portfolio.common.exception.AboutMeNotFoundException;

@Service
@Transactional
public class AboutMeService implements IAboutMeService {
	@Autowired
	private AboutMeRepository aboutMeRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<AboutMeDTO> getAllList() {
		return null;
	}

	@Override
	public AboutMeDTO save(AboutMeDTO dto) {
		AboutMe entity = new AboutMe();
		BeanUtils.copyProperties(dto, entity);
		BeanUtils.copyProperties(aboutMeRepo.save(entity), dto);
		return dto;
	}

	@Override
	public AboutMeDTO getDetails(String id) {
		AboutMe getDetails = aboutMeRepo.findById(id).get();
		AboutMeDTO dto = new AboutMeDTO();
		BeanUtils.copyProperties(getDetails, dto);
		return dto;
	}

	@Override
	public boolean changePassword(ChangePasswordDTO dto) {
		try {
			AboutMe getAboutMeDetails = aboutMeRepo.findById(SystemConstant.ABOUT_ME_ID).get();
			getAboutMeDetails.setPassword(passwordEncoder.encode(dto.getNewPassword()));
			aboutMeRepo.save(getAboutMeDetails);
			return true;
		} catch (Exception e) {
			System.out.println("Error when change password");
			return false;
		}
	}

	@Override
	public boolean checkOldPassword(String oldPassword) {
		AboutMe getAboutMeDetails = aboutMeRepo.findById(SystemConstant.ABOUT_ME_ID).get();
		return !passwordEncoder.matches(oldPassword, getAboutMeDetails.getPassword()) ? false : true;
	}

	@Override
	public void updateResetPasswordToken(String email, String token) throws AboutMeNotFoundException {
		AboutMe getAboutMeObjByEmail = aboutMeRepo.findByEmailAndStatus(email, 1);
		if (getAboutMeObjByEmail != null) {
			getAboutMeObjByEmail.setResetPasswordToken(token);
			aboutMeRepo.save(getAboutMeObjByEmail);
		} else {
			throw new AboutMeNotFoundException("Could not find any account with the email " + email);
		}
	}

	@Override
	public AboutMe getByResetPasswordToken(String token) {
		return aboutMeRepo.findByResetPasswordToken(token);
	}

	@Override
	public void updatePassword(AboutMe entity, String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		entity.setPassword(passwordEncoder.encode(password));
		entity.setResetPasswordToken(null);
		aboutMeRepo.save(entity);
	}
}
