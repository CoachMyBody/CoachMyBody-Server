package com.coachmybody.user.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachmybody.user.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findBySocialId(String socialId);

}
