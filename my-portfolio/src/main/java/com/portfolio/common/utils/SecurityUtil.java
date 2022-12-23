package com.portfolio.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.portfolio.admin.dto.AccountDetails;

public class SecurityUtil {
	public static AccountDetails getPrincipal() {
		return (AccountDetails) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
	}
}
