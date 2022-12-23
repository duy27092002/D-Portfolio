package com.portfolio.admin.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.portfolio.admin.customAnnotation.CheckDate;
import com.portfolio.admin.entity.LanguageAndTechnology;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectDTO extends BaseDTO {
	@NotBlank(message = "Name must not empty")
	private String name;

	@NotBlank(message = "Short description must not empty")
	@Length(max = 500, message = "Short description must not exceed 500 characters")
	private String shortDesc;

	@NotEmpty(message = "Please choose language or technology to use")
	private String[] ltIdList;

	@CheckDate
	private String startDateStr;

	private String endDateStr;

	@NotBlank(message = "Github link of project must not empty")
	private String github;

	private String deployLink;

	// response
	private Date startDate;

	private Date endDate;

	private List<LanguageAndTechnology> languagesAndTechnologies = new ArrayList<>();
}
