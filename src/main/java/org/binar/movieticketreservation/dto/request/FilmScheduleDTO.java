package org.binar.movieticketreservation.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmScheduleDTO {
    @NotBlank(message = "filmId should not be NULL or EMPTY")
    private String filmId;
    @NotBlank(message = "studioId should not be NULL or EMPTY")
    private String studioId;
    @NotBlank(message = "startTime should not be NULL or EMPTY")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @NotBlank(message = "endTime should not be NULL or EMPTY")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
