package com.portfolio.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "website")
@Getter
@Setter
public class Website extends BaseEntity {
	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 500)
	private String favicon;
	
	@Column(length = 500)
	private String logo;
}
