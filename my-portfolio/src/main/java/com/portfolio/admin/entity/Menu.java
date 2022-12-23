package com.portfolio.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu extends BaseEntity {
	@Column(length = 20, nullable = false)
	private String name;

	@Column
	private String urlCode;
}
