package com.portfolio.common.utils;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class SortUtil {
	public Sort handleSort(String orderBy, String orderType) {
		Sort sort = null;
		if (orderType.toLowerCase().equalsIgnoreCase("asc")) {
			sort = Sort.by(orderBy).ascending();
		} else {
			sort = Sort.by(orderBy).descending();
		}
		return sort;
	}
}
