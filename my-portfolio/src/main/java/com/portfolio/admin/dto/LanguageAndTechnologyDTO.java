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
public class LanguageAndTechnologyDTO extends BaseDTO {
	@NotBlank(message = "Name must not empty")
	private String name;
}
