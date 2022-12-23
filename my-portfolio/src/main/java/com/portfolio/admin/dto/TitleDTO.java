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
public class TitleDTO extends BaseDTO {
	@NotBlank(message = "Title name must not empty")
	@Length(max = 255, message = "Title name must not exceed 255 characters")
	private String name;
	
	private int titleIndex;
}
