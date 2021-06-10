package com.coachmybody.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
public class UserToBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "badge_id")
    private Long badgeId;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
}