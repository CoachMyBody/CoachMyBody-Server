package com.coachmybody.user.domain;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
public class UserExerciseLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "uuid-char")
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "exer_level_id")
    private int exerLevelId;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
}