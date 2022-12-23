package com.portfolio.admin.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.portfolio.admin.customAnnotation.CheckDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CertificateDTO extends BaseDTO {
	@NotBlank(message = "Certificate name must not empty")
	@Length(max = 500, message = "Certificate must not exceed 500 characters")
	private String name;
	
	@CheckDate
	private String timeStr;

	// response
	private Date time;
}
