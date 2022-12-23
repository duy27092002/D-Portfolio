package com.portfolio.admin.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResumeDTO extends BaseDTO {
	@NotBlank(message = "This field must not empty")
	private String pdfLinkDrive;
}
