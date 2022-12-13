package org.binar.movieticketreservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmAndScheduleResponseDTO {
    private String filmName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String studioName;
}
