package com.portfolio.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cetificate")
@Getter
@Setter
public class Certificate extends BaseEntity {
	@Column(length = 500, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Date time;
}
