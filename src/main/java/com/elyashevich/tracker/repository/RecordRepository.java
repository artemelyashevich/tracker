package com.elyashevich.tracker.repository;

import com.elyashevich.tracker.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecordRepository extends JpaRepository<Record, UUID> {
}