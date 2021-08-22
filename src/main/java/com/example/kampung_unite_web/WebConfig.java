package com.example.kampung_unite_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.kampung_unite_web.interceptor.LoggingInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private LoggingInterceptor login;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(login).addPathPatterns("/**").excludePathPatterns("/", "/login/**", "/css/**",
//				"/image/**", "/user/**");
		registry.addInterceptor(login).addPathPatterns("/**").excludePathPatterns("/", "/login/**", "/css/**",
				"/image/**", "/user/**", "/cplist/**", "/groceries/**", "/grocerylists/**", "/groupplan/**",
				"/HitcherDetail/**", "/hitchrequest/**", "/products/**", "");
	}

}
