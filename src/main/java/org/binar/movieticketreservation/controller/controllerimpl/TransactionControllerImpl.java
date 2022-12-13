package org.binar.movieticketreservation.controller.controllerimpl;

import lombok.extern.slf4j.Slf4j;
import org.binar.movieticketreservation.controller.TransactionController;
import org.binar.movieticketreservation.dto.APIResponse;
import org.binar.movieticketreservation.dto.request.TransactionRequestDTO;
import org.binar.movieticketreservation.dto.request.TransactionUpdateStatusRequestDTO;
import org.binar.movieticketreservation.service.serviceimpl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/transactions")
@Slf4j
public class TransactionControllerImpl implements TransactionController {
        @Autowired
        private TransactionServiceImpl transactionServiceImpl;

        @Operation(
                summary = "Create a new Transaction", 
                description = "Insert a new Transaction into Database", 
                tags = "Transactions",
                security = {@SecurityRequirement(name = "bearer-key")})
        @Override
        @PostMapping(value = "/orders")
        public ResponseEntity<APIResponse> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
                log.info("TransactionController:: createTransaction request body");

                String result = transactionServiceImpl.saveTransaction(transactionRequestDTO);
                APIResponse<String> response = APIResponse
                                .<String>builder()
                                .status("SUCCESS")
                                .results(result)
                                .build();

                log.info("TransactionController::createTransaction success");
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }

        @Operation(
                summary = "Update transaction status", 
                description = "Update transaction status by transaction id", 
                tags = "Transactions",
                security = {@SecurityRequirement(name = "bearer-key")})
        @PutMapping(value = "/{transactionId}")
        @Override
        public ResponseEntity<APIResponse> updateStatusTransaction(
                        @RequestBody TransactionUpdateStatusRequestDTO transactionUpdateStatusRequestDTO,
                        @PathVariable String transactionId) {

                log.info("TransactionController:: updateTransaction request body");

                String result = transactionServiceImpl.updateStatusTransaction(
                                transactionUpdateStatusRequestDTO.getStatus(),
                                transactionId);
                APIResponse<String> response = APIResponse
                                .<String>builder()
                                .status("SUCCESS")
                                .results(result)
                                .build();

                log.info("TransactionController::updateTransaction success");
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
}
