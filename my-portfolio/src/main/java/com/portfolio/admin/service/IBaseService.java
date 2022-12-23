package com.portfolio.admin.service;

import java.util.List;

public interface IBaseService<T> {
	List<T> getAllList();
	
	T save(T dto);
	
	T getDetails(String id);
}
