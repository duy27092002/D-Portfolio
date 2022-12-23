package com.portfolio.admin.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IdParameterDTO {
	@NotBlank(message = "Please select one choice")
	private String firstId;
	
	private String secondId;
}
