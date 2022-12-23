package com.portfolio.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.admin.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, String> {

}
