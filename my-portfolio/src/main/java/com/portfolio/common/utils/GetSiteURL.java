package com.portfolio.common.utils;

import javax.servlet.http.HttpServletRequest;

public class GetSiteURL {
	public static String getSiteUrl(HttpServletRequest request) {
		String siteUrl = request.getRequestURL().toString();
		return siteUrl.replace(request.getServletPath(), "");
	}
}
