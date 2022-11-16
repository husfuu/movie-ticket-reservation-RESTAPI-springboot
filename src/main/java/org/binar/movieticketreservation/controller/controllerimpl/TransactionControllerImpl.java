package org.binar.movieticketreservation.controller.controllerimpl;

import java.util.HashMap;
import java.util.Map;

import org.binar.movieticketreservation.controller.TransactionController;
import org.binar.movieticketreservation.dto.TransactionServiceInput;
import org.binar.movieticketreservation.dto.request.OrderRequestDto;
import org.binar.movieticketreservation.dto.request.TransactionUpdateStatusDto;
import org.binar.movieticketreservation.entity.TransactionStatus;
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

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionControllerImpl implements TransactionController {

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @Override
    @PostMapping(value = "/orders")
    public ResponseEntity<?> order(@RequestBody OrderRequestDto orderRequestDto) {

        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "create film success");

        try {
            TransactionServiceInput transactionServiceInput = new TransactionServiceInput();
            transactionServiceInput.setUserId(orderRequestDto.getUserId());
            transactionServiceInput.setFilmId(orderRequestDto.getFilmId());
            transactionServiceInput.setScheduleId(orderRequestDto.getScheduleId());
            transactionServiceInput.setStudioId(orderRequestDto.getStudioId());

            transactionServiceImpl.saveTransaction(transactionServiceInput);
            return new ResponseEntity<>(
                    resp,
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            resp.put("message", "fail to create film : " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{transactionId}")
    @Override
    public ResponseEntity<?> updateStatusTransaction(
            @RequestBody TransactionUpdateStatusDto transactionUpdateStatusDto,
            @PathVariable String transactionId) {

        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "create transaction success");

        try {
            transactionServiceImpl.updateStatusTransaction(transactionUpdateStatusDto.getStatus(), transactionId);
            return new ResponseEntity<>(
                    resp,
                    HttpStatus.ACCEPTED);

        } catch (Exception e) {
            resp.put("message", "fail to update transaction status : " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
