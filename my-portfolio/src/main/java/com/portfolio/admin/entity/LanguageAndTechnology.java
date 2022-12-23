package com.portfolio.admin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "language_and_technology")
@Getter
@Setter
public class LanguageAndTechnology extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@ManyToMany(mappedBy = "languagesAndTechnologies", cascade = CascadeType.ALL)
	private List<Project> projectList = new ArrayList<>();
}
