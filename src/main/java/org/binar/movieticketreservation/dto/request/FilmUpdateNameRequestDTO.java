package org.binar.movieticketreservation.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FilmUpdateNameRequestDTO {
    @NotBlank(message = "filmName should not be NULL or EMPTY")
    private String filmName;
}
