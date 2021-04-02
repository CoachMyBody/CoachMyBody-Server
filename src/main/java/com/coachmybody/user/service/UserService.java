package com.coachmybody.user.service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coachmybody.user.domain.User;
import com.coachmybody.user.domain.UserAuth;
import com.coachmybody.user.domain.repository.UserAuthRepository;
import com.coachmybody.user.domain.repository.UserRepository;
import com.coachmybody.user.interfaces.dto.AuthResponse;
import com.coachmybody.user.interfaces.dto.LoginRequest;
import com.coachmybody.user.interfaces.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserAuthRepository userAuthRepository;

	public void register(RegisterRequest request) {
		if (userRepository.findBySocialId(request.getSocialId()).isPresent()) {
			throw new IllegalArgumentException();
		}

		User user = User.of(request);

		userRepository.save(user);
	}

	public AuthResponse login(LoginRequest request) {
		String socialId = request.getSocialId();

		User user = userRepository.findBySocialId(socialId)
			.orElseThrow(NoSuchElementException::new);

		Long userId = user.getId();

		UserAuth newAuth = UserAuth.newAuth(userId);

		Optional<UserAuth> existUserAuth = userAuthRepository.findByUserId(userId);
		if (existUserAuth.isPresent()) {
			newAuth = existUserAuth.get();
			newAuth.refresh();
		}

		userAuthRepository.save(newAuth);

		return AuthResponse.of(newAuth);
	}


	public boolean isValidToken(String accessToken) {
		Optional<UserAuth> optionalUserAuth = userAuthRepository.findByAccessToken(accessToken);

		if (optionalUserAuth.isEmpty()) {
			return false;
		}

		UserAuth userAuth = optionalUserAuth.get();

		Instant now = Instant.now();

		if (userAuth.getExpiredAt().isBefore(now)) {
			return false;
		}

		return true;
	}

}
