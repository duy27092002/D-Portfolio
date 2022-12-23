package com.portfolio.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "component")
@Getter
@Setter
public class Component extends BaseEntity {
	@Column(nullable = false)
	private int cIndex;

	@ManyToOne
	@JoinColumn(name = "title_id", nullable = false)
	private Title title;

	@Column
	private String subTitle;

	@Column(columnDefinition = "TEXT")
	private String content;

	@Column(length = 500)
	private String image;
}
