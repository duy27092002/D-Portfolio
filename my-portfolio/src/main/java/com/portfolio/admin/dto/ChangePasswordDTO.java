package com.portfolio.admin.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangePasswordDTO {
	@NotBlank(message = "Please enter your old password")
	private String oldPassword;
	
	@Length(min = 6, message = "Password must contains at least 6 characters")
	private String newPassword;
}
