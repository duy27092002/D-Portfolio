package com.portfolio.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.admin.entity.Website;

public interface WebsiteRepository extends JpaRepository<Website, String> {

}
