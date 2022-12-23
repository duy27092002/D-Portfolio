package com.portfolio.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.portfolio.admin.entity.Component;

public interface ComponentRepository extends SearchingRepository<Component, String> {
	@Query("select co from #{#entityName} co where co.subTitle like %?1%")
	Page<Component> getSearchList(String keyword, Pageable pageable);

	@Query("select co from #{#entityName} co")
	Page<Component> getAllList(Pageable pageable);

	Component findBySubTitle(String subTitle);

	Component findBycIndex(int cIndex);
	
	List<Component> findByStatus(int status);
}
