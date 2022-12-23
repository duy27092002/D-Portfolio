package com.portfolio.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "about_me")
@Getter
@Setter
public class AboutMe extends BaseEntity {
	@Column(length = 50, nullable = false)
	private String name;

	@Column(nullable = false)
	private String major;

	@Column(length = 20, nullable = false)
	private String phone;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String github;

	@Column
	private String facebook;

	@Column
	private String instagram;

	@Column
	private String linkedIn;

	@Column
	private String resetPasswordToken;
}
