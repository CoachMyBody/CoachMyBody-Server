package com.coachmybody.user.domain.repository;

import com.coachmybody.user.domain.UserToBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserToBadgeRepository extends JpaRepository<UserToBadge, Long> {
}
