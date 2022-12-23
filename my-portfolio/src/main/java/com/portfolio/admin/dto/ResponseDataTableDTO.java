package com.portfolio.admin.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.portfolio.admin.repository.SearchingRepository;
import com.portfolio.common.utils.SortUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDataTableDTO {
	private String path;

	private Integer currentPage;

	private Integer pageSize;

	private Integer totalOfPage;

	private Integer totalOfItem;

	private String orderBy;

	private String orderType;

	private String keyword;

	private List<?> resultList;

	public ResponseDataTableDTO(String path, Integer currentPage, Integer pageSize, String orderBy, String orderType,
			String keyword) {
		this.path = path;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.orderBy = orderBy;
		this.orderType = orderType;
		this.keyword = keyword;
	}

	public <T> ResponseDataTableDTO getList(SearchingRepository<?, ?> repository, Class<T> dtoObj, String keyword)
			throws Exception {
		SortUtil sortUtil = new SortUtil();

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sortUtil.handleSort(orderBy, orderType));

		List<T> resultList = new ArrayList<>();

		Page<?> getListByPage = null;

		if (keyword == null) {
			getListByPage = repository.getAllList(pageable);
		} else {
			getListByPage = repository.getSearchList(keyword, pageable);
		}

		for (Object object : getListByPage) {
			@SuppressWarnings("deprecation")
			T dto = dtoObj.newInstance();
			BeanUtils.copyProperties(object, dto);
			resultList.add(dto);
		}

		this.setResultList(resultList);
		this.setTotalOfItem((int) getListByPage.getTotalElements());
		this.setTotalOfPage(getListByPage.getTotalPages());

		return this;
	}
}
