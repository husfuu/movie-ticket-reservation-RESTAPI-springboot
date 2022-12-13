package org.binar.movieticketreservation.service;

import java.util.List;

import org.binar.movieticketreservation.dto.response.FilmAndScheduleResponseDTO;
import org.binar.movieticketreservation.dto.response.FilmResponseDTO;
import org.binar.movieticketreservation.dto.request.FilmRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface FilmService {

    FilmResponseDTO saveFilm(FilmRequestDTO filmRequestDTO);

    FilmResponseDTO saveFilmProvided(FilmRequestDTO filmRequestDTO);

    FilmResponseDTO updateFilmName(String filmName, String filmId) throws Exception;

    String deleteFilmById(String filmId) throws Exception;

    // customer view
    List<FilmResponseDTO> getAllFilms();

    // customer view
    List<FilmAndScheduleResponseDTO> getScheduleByFilmId(String filmId);
}
