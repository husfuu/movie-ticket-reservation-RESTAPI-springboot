package org.binar.movieticketreservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmProvidedRequestDTO {
    @NotBlank(message = "filmId should not be NULL or EMPTY")
    private Long filmId;
    @NotBlank(message = "ticket price should not be NULL OR EMPTY")
    @Min(value = 45000, message = "ticket price cannot be less than 45,000")
    @Max(value = 70000, message = "ticket price cannot be greater than 70,000")
    private Double ticketPrice;
}
