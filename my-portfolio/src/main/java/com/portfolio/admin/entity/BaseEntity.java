package com.portfolio.admin.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity {
	@Id
	@Column(length = 10)
	@GeneratedValue(generator = "id_generator")
	@GenericGenerator(name = "id_generator", strategy = "com.portfolio.common.utils.IdGenerator")
	private String id;

	@Column(columnDefinition = "INT default 1")
	private int status;
}
