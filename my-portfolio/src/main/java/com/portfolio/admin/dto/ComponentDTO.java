package com.portfolio.admin.dto;

import org.springframework.web.multipart.MultipartFile;

import com.portfolio.admin.entity.Title;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComponentDTO extends BaseDTO {
	private int cIndex;

	private String titleId;

	private String subTitle;

	private String content;

	private String image;
	MultipartFile imageFile;

	// response
	private Title title;
}
