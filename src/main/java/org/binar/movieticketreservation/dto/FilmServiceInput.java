package org.binar.movieticketreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmServiceInput {
    private String name;
    private String overview;
    private double voteAverage;
    private Double ticketPrice;
    private boolean isOnShow = Boolean.TRUE;
}
