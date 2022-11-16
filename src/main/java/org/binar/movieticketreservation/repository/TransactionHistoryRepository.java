package org.binar.movieticketreservation.repository;

import org.binar.movieticketreservation.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, String> {
}
