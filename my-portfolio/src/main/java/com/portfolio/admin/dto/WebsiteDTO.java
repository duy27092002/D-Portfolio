package com.portfolio.admin.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebsiteDTO extends BaseDTO {
	@NotBlank(message = "Website name must not empty")
	@Length(max = 50, message = "Website name must not exceed 50 characters")
	private String name;

	private String favicon;
	MultipartFile faviconFile;

	private String logo;
	MultipartFile logoFile;
}
