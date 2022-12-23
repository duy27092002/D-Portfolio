package com.portfolio.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.admin.entity.AboutMe;

public interface AboutMeRepository extends JpaRepository<AboutMe, String> {
	AboutMe findByEmailAndStatus(String email, int status);
	
	AboutMe findByResetPasswordToken(String token);
}
