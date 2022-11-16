package org.binar.movieticketreservation.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmServiceOutput {
    private String filmName;
    private LocalDateTime showTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String studioName;
}
