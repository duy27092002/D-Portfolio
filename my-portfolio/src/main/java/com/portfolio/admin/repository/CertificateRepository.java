package com.portfolio.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.portfolio.admin.entity.Certificate;

public interface CertificateRepository extends SearchingRepository<Certificate, String> {
	@Query("select c from #{#entityName} c where c.name like %?1%")
	Page<Certificate> getSearchList(String keyword, Pageable pageable);
	
	@Query("select c from #{#entityName} c")
	Page<Certificate> getAllList(Pageable pageable);
	
	Certificate findByName(String name);
	
	List<Certificate> findByStatus(int status);
}
