package com.atteam.atshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.atteam.atshop.interceptor.GlobalInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	@Autowired
	GlobalInterceptor globalInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*
		 * 	.addPathPatterns     : chỉ ra các url nào mà chúng ta muốn interceptor thực hiện
		 * 	.excludePathPatterns : loại trừ ra 1 số url mà không cần thiết interceptor thực hiện
		 * 
		 * */
		registry.addInterceptor(globalInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/rest/**", "/admin/**", "/assets/**");
	}
}
