package com.coachmybody.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.coachmybody.common.exception.InvalidAccessTokenException;
import com.coachmybody.user.application.UserService;

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

		return true;
		/*String authorization = request.getHeader("Authorization");

		List<String> permitHost = List.of("0:0:0:0:0:0:0:1", "127.0.0.1");

		if (permitHost.contains(request.getRemoteHost())) {
			return true;
		}

		if (authorization != null) {
			String token = authorization.substring(TOKEN_PREFIX);
			if (userService.isValidToken(token)) {
				return true;
			}
		}

		throw new InvalidAccessTokenException();*/

	}
}
