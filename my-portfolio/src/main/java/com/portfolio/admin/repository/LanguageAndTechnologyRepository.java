package com.portfolio.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.portfolio.admin.entity.LanguageAndTechnology;

public interface LanguageAndTechnologyRepository extends SearchingRepository<LanguageAndTechnology, String> {
	@Query("select lt from #{#entityName} lt where lt.name like %?1%")
	Page<LanguageAndTechnology> getSearchList(String keyword, Pageable pageable);
	
	@Query("select lt from #{#entityName} lt")
	Page<LanguageAndTechnology> getAllList(Pageable pageable);
	
	LanguageAndTechnology findByName(String name);
	
	List<LanguageAndTechnology> findByStatus(int status);
}
