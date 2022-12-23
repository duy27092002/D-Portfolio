package com.portfolio.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.portfolio.admin.entity.Project;

public interface ProjectRepository extends SearchingRepository<Project, String> {
	@Query("select p from #{#entityName} p where p.name like %?1%")
	Page<Project> getSearchList(String keyword, Pageable pageable);

	@Query("select p from #{#entityName} p")
	Page<Project> getAllList(Pageable pageable);

	Project findByName(String name);

	List<Project> findByStatusOrderByStartDateDesc(int status);
}
