package org.binar.movieticketreservation.dto.request;

import org.binar.movieticketreservation.entity.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionUpdateStatusDto {
    private TransactionStatus status;
}
