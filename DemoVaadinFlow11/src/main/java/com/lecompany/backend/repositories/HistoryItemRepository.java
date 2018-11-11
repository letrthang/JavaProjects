package com.lecompany.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lecompany.backend.data.entity.HistoryItem;

public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
}
