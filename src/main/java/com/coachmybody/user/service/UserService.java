package com.coachmybody.user.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coachmybody.common.exception.DuplicatedEntityException;
import com.coachmybody.common.exception.InvalidAccessTokenException;
import com.coachmybody.common.exception.InvalidRefreshTokenException;
import com.coachmybody.common.exception.NotFoundEntityException;
import com.coachmybody.user.domain.User;
import com.coachmybody.user.domain.UserAuth;
import com.coachmybody.user.domain.repository.UserAuthRepository;
import com.coachmybody.user.domain.repository.UserRepository;
import com.coachmybody.user.interfaces.dto.AuthResponse;
import com.coachmybody.user.interfaces.dto.LoginRequest;
import com.coachmybody.user.interfaces.dto.RefreshRequest;
import com.coachmybody.user.interfaces.dto.RegisterRequest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserAuthRepository userAuthRepository;

	public void register(RegisterRequest request) {
		if (userRepository.findBySocialId(request.getSocialId()).isPresent()) {
			throw new DuplicatedEntityException();
		}

		User user = User.of(request);

		userRepository.save(user);
	}

	public AuthResponse login(@NonNull final String socialId) {
		User user = userRepository.findBySocialId(socialId)
			.orElseThrow(NotFoundEntityException::new);

		String userId = user.getId();

		UserAuth newAuth = UserAuth.newAuth(userId);

		Optional<UserAuth> existUserAuth = userAuthRepository.findByUserId(userId);
		if (existUserAuth.isPresent()) {
			newAuth = existUserAuth.get();
			newAuth.refresh();
		}

		userAuthRepository.save(newAuth);

		return AuthResponse.of(newAuth);
	}


	public boolean isValidToken(@NonNull final String accessToken) {
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

	public AuthResponse refresh(@NonNull final String refreshToken) {
		UserAuth userAuth = userAuthRepository.findByRefreshToken(refreshToken)
			.orElseThrow(InvalidRefreshTokenException::new);

		userAuth.refresh();

		userAuthRepository.save(userAuth);

		return AuthResponse.of(userAuth);
	}
}
