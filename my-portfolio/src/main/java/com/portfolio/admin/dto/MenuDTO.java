package com.portfolio.admin.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MenuDTO extends BaseDTO {
	@NotBlank(message = "Name must not empty")
	@Length(max = 20, message = "Name must not exceed 20 characters")
	private String name;
	
	private String urlCode;
}
