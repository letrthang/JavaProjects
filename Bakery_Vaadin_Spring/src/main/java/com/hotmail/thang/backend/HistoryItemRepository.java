package com.hotmail.thang.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotmail.thang.backend.data.entity.HistoryItem;

public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
}
