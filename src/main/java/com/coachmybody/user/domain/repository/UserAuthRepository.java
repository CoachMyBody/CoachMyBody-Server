package com.coachmybody.user.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachmybody.user.domain.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

	Optional<UserAuth> findByUserId(Long userId);

	Optional<UserAuth> findByAccessToken(String accessToken);
}
