package com.portfolio.admin.paging;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.portfolio.admin.dto.ResponseDataTableDTO;

public class PagingResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(PagingParam.class) != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		try {
			PagingParam pagingParam = parameter.getParameterAnnotation(PagingParam.class);

			String getPath = pagingParam.path();

			String getPageParam = webRequest.getParameter("page");
			String getPageStr = getPageParam == null ? String.valueOf(pagingParam.page()) : getPageParam.trim();
			Integer getPage = Integer.parseInt(getPageStr);

			String getPageSizeParam = webRequest.getParameter("pageSize");
			String getPageSizeStr = getPageSizeParam == null ? String.valueOf(pagingParam.pageSize())
					: getPageSizeParam.trim();
			Integer getPageSize = Integer.parseInt(getPageSizeStr);

			String getOrderByParam = webRequest.getParameter("orderBy");
			String getOrderBy = getOrderByParam == null ? pagingParam.orderBy() : getOrderByParam.trim();

			String getOrderTypeParam = webRequest.getParameter("orderType");
			String getOrderType = getOrderTypeParam == null ? pagingParam.orderType()
					: getOrderTypeParam.toLowerCase().trim();

			String getKeyword = webRequest.getParameter("keyword");

			return new ResponseDataTableDTO(getPath, getPage, getPageSize, getOrderBy, getOrderType, getKeyword);
		} catch (Exception ex) {
			System.out.println("Error at PagingResolver!");
			ex.printStackTrace();
			return null;
		}
	}

}
