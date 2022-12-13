package org.binar.movieticketreservation.repository;

import org.binar.movieticketreservation.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
