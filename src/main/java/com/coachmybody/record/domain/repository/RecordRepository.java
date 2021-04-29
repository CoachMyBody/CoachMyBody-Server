package com.coachmybody.record.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coachmybody.record.domain.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {
}