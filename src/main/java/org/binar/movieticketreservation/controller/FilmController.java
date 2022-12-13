package org.binar.movieticketreservation.controller;

import org.binar.movieticketreservation.dto.APIResponse;
import org.binar.movieticketreservation.dto.request.FilmProvidedRequestDTO;
import org.binar.movieticketreservation.dto.request.FilmRequestDTO;
import org.binar.movieticketreservation.dto.request.FilmUpdateNameRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface FilmController {
    ResponseEntity<APIResponse> getAllUpcomingFilms();

    ResponseEntity<APIResponse> createFilm(FilmRequestDTO filmRequestDto);

    ResponseEntity<APIResponse> addFilmProvided(FilmProvidedRequestDTO filmProvidedRequestDto);

    ResponseEntity<APIResponse> updateFilmName(FilmUpdateNameRequestDTO filmUpdateNameRequestDto, String filmId)
            throws Exception;

    ResponseEntity<APIResponse> deleteFilm(String filmId) throws Exception;

    ResponseEntity<APIResponse> getAllFilms();

    ResponseEntity<APIResponse> getScheduleByFilmId(String filmId);
}
