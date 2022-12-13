package org.binar.movieticketreservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmResponseDTO {
    private String name;
    private String overview;
    private Double voteAverage;
    private Double ticketPrice;
    private boolean isOnShow;
}
