package org.binar.movieticketreservation.controller;

import org.binar.movieticketreservation.dto.APIResponse;
import org.binar.movieticketreservation.dto.request.TransactionRequestDTO;
import org.binar.movieticketreservation.dto.request.TransactionUpdateStatusRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface TransactionController {
    ResponseEntity<APIResponse> createTransaction(TransactionRequestDTO transactionRequestDTO);

    ResponseEntity<APIResponse> updateStatusTransaction(
            TransactionUpdateStatusRequestDTO transactionUpdateStatusRequestDTO,
            String transactionId);
}
