package com.portfolio.admin.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AboutMeDTO extends BaseDTO {
	@NotBlank(message = "Name must not empty")
	@Length(max = 50, message = "Name must contains from 1 to 50 characters")
	private String name;

	@NotBlank(message = "Major must not empty")
	private String major;

	@NotBlank(message = "Phone must not empty")
	@Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Phone number is invalid")
	private String phone;

	@NotBlank(message = "Email must not empty")
	@Length(max = 50, message = "Email must not exceed 50 characters")
	@Email(message = "Email format is incorrect")
	private String email;
	
	@NotBlank(message = "Password must not empty")
	@Length(min = 6, message = "Password must contains at least 6 characters")
	private String password;

	@NotBlank(message = "Github account must not empty")
	private String github;

	private String facebook;

	private String instagram;

	private String linkedIn;
}
