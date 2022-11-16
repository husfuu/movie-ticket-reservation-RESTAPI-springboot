package org.binar.movieticketreservation.controller;

import org.binar.movieticketreservation.dto.request.OrderRequestDto;
import org.binar.movieticketreservation.dto.request.TransactionUpdateStatusDto;
import org.binar.movieticketreservation.entity.TransactionStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface TransactionController {
    ResponseEntity<?> order(OrderRequestDto orderRequestDto);

    ResponseEntity<?> updateStatusTransaction(
            TransactionUpdateStatusDto transactionUpdateStatusDto,
            String transactionId);
}
