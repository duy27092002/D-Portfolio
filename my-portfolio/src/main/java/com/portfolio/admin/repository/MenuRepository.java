package com.portfolio.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.portfolio.admin.entity.Menu;

public interface MenuRepository extends SearchingRepository<Menu, String> {
	@Query("select m from #{#entityName} m where m.name like %?1%")
	Page<Menu> getSearchList(String keyword, Pageable pageable);
	
	@Query("select m from #{#entityName} m")
	Page<Menu> getAllList(Pageable pageable);
	
	Menu findByUrlCode(String urlCode);
	
	List<Menu> findByStatus(int status);
}
