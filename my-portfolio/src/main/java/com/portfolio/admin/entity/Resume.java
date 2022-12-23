package com.portfolio.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resume")
@Getter
@Setter
public class Resume extends BaseEntity {
	@Column(nullable = false)
	private String pdfLinkDrive;
}
