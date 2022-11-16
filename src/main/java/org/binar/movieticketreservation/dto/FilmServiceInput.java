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
public class FilmServiceInput {
    private String filmName;
    private boolean isOnShow;
    private LocalDateTime showTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
