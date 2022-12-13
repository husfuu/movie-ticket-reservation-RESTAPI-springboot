package org.binar.movieticketreservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionRequestDTO {
    @NotBlank(message = "filmId should not be NULL or EMPTY")
    private String filmId;
    @NotBlank(message = "scheduleId should not be NULL or EMPTY")
    private String scheduleId;
    @NotBlank(message = "studioId should not be NULL or EMPTY")
    private String studioId;
    @NotBlank(message = "userId should not be NULL or EMPTY")
    private String userId;
}
