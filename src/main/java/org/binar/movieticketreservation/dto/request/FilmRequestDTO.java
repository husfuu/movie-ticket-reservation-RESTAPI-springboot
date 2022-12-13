package org.binar.movieticketreservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmRequestDTO {
    @NotBlank(message = "name should not be NULL or EMPTY")
    private String name;
    private String overview = "-";
    private Double voteAverage = 0.0;
    @NotNull(message = "ticket price should not be NULL OR EMPTY")
    @Min(value = 45000, message = "ticket price cannot be less than 45,000")
    @Max(value = 70000, message = "ticket price cannot be less than 70,000")
    private Double ticketPrice;
    private boolean isOnShow = Boolean.TRUE;
}
