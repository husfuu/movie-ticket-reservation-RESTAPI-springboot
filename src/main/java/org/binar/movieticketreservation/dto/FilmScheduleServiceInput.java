package org.binar.movieticketreservation.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmScheduleServiceInput {
    private String filmId;
    private String studioId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
