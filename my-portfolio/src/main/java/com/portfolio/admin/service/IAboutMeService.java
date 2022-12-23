package com.portfolio.admin.service;

import com.portfolio.admin.dto.AboutMeDTO;
import com.portfolio.admin.dto.ChangePasswordDTO;
import com.portfolio.admin.entity.AboutMe;

public interface IAboutMeService extends IBaseService<AboutMeDTO> {
	boolean checkOldPassword(String oldPassword);
	
	boolean changePassword(ChangePasswordDTO dto);
	
	void updateResetPasswordToken(String email, String token);
	
	AboutMe getByResetPasswordToken(String token);
	
	void updatePassword(AboutMe entity, String password);
}
