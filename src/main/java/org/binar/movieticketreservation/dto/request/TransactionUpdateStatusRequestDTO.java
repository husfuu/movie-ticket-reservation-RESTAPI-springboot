package org.binar.movieticketreservation.dto.request;

import org.binar.movieticketreservation.entity.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionUpdateStatusRequestDTO {
    @NotBlank(message = "status should not be NULL or EMPTY")
    private TransactionStatus status;
}
