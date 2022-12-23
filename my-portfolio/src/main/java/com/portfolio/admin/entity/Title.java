package com.portfolio.admin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "title")
@Getter
@Setter
public class Title extends BaseEntity {
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int titleIndex;

	@OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
	private List<Component> componentList = new ArrayList<>();
}
