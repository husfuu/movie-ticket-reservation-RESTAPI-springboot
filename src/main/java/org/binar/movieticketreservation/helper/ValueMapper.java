package org.binar.movieticketreservation.helper;

import org.binar.movieticketreservation.dto.response.FilmResponseDTO;
import org.binar.movieticketreservation.entity.Film;

public class ValueMapper {
    public static FilmResponseDTO convertToFilmResponseDTO(Film film) {
        FilmResponseDTO filmResponseDTO = new FilmResponseDTO();
        filmResponseDTO.setName(film.getName());
        filmResponseDTO.setOverview(film.getOverview());
        filmResponseDTO.setVoteAverage(film.getVoteAverage());
        filmResponseDTO.setTicketPrice(film.getTicketPrice());
        filmResponseDTO.setOnShow(film.getIsOnShow());
        return filmResponseDTO;
    }
}
