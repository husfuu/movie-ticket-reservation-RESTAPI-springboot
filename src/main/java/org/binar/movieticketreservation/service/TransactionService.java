package org.binar.movieticketreservation.service;

import org.binar.movieticketreservation.dto.request.TransactionRequestDTO;
import org.binar.movieticketreservation.entity.TransactionStatus;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    String saveTransaction(TransactionRequestDTO transactionRequestDTO);

    String updateStatusTransaction(TransactionStatus tStatus, String transactionId);
}
