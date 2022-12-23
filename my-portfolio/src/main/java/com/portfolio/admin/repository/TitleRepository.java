package com.portfolio.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.portfolio.admin.entity.Title;

public interface TitleRepository extends SearchingRepository<Title, String> {
	@Query("select t from #{#entityName} t where t.name like %?1%")
	Page<Title> getSearchList(String keyword, Pageable pageable);
	
	@Query("select t from #{#entityName} t")
	Page<Title> getAllList(Pageable pageable);
	
	Title findByName(String name);
	
	List<Title> findByStatus(int status);
	
	Title findByTitleIndex(int titleIndex);
}
