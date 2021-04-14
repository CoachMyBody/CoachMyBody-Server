package com.coachmybody.common.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coachmybody.common.interceptor.SecurityInterceptor;
import com.coachmybody.user.application.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig implements WebMvcConfigurer {

	private final UserService userService;

	@Bean
	public SecurityInterceptor securityInterceptor() {
		return new SecurityInterceptor(userService);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor())
			.addPathPatterns("/api/**")
			.excludePathPatterns(List.of(
					"/api/v1/auth/**"
				));
	}
}
