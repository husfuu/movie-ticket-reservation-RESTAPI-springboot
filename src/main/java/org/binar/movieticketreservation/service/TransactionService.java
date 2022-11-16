package org.binar.movieticketreservation.service;

import org.binar.movieticketreservation.dto.TransactionServiceInput;
import org.binar.movieticketreservation.entity.TransactionStatus;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    void saveTransaction(TransactionServiceInput transactionServiceInput) throws Exception;

    void updateStatusTransaction(TransactionStatus tStatus, String transactionId) throws Exception;
}
