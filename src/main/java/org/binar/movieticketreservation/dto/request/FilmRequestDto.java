package org.binar.movieticketreservation.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmRequestDto {
    private String film_name;
    private boolean is_on_show;
    // private LocalDateTime show_time;
    // private LocalDateTime start_time;
    // private LocalDateTime end_time;
}
