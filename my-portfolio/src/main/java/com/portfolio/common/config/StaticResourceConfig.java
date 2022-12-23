package com.portfolio.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.portfolio.common.constant.SystemConstant;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image-file/**").addResourceLocations("file:/" + SystemConstant.IMG_FOLDER);
	}

}
