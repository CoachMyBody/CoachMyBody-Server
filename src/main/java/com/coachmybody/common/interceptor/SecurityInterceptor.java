package com.coachmybody.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.coachmybody.common.exception.InvalidAccessTokenException;
import com.coachmybody.user.service.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SecurityInterceptor implements HandlerInterceptor {

	private static final int TOKEN_PREFIX = 7; // BEARER
	private final UserService userService;

	@Override
	public boolean preHandle(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull Object handler) {

		String authorization = request.getHeader("Authorization");

		if (authorization != null) {
			String token = authorization.substring(TOKEN_PREFIX);
			if (userService.isValidToken(token)) {
				return true;
			}
		}

		throw new InvalidAccessTokenException();
	}
}
