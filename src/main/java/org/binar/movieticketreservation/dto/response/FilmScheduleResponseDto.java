package org.binar.movieticketreservation.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmScheduleResponseDto {
    private String name;
    private LocalDateTime showTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String studioName;
}
