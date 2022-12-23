package com.portfolio.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "project")
@Getter
@Setter
public class Project extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@Column(length = 500)
	private String shortDesc;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "project_language_technology", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "lt_id"))
	private List<LanguageAndTechnology> languagesAndTechnologies = new ArrayList<>();

	@Column(nullable = false)
	private Date startDate;

	@Column
	private Date endDate;

	@Column(nullable = false)
	private String github;

	@Column
	private String deployLink;

	public void addLanguagesAndTechnologies(LanguageAndTechnology lt) {
		this.languagesAndTechnologies.add(lt);
		lt.getProjectList().add(this);
	}
}
