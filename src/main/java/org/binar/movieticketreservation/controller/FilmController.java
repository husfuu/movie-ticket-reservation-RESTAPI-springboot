package org.binar.movieticketreservation.controller;

import org.binar.movieticketreservation.dto.request.FilmRequestDto;
import org.binar.movieticketreservation.dto.request.FilmUpdateNameRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface FilmController {
    ResponseEntity<?> createFilm(FilmRequestDto filmRequestDto);

    ResponseEntity<?> updateFilmName(FilmUpdateNameRequestDto filmUpdateNameRequestDto, String filmId);

    ResponseEntity<?> deleteFilm(String filmId);

    ResponseEntity<?> getAllFilms();

    ResponseEntity<?> getScheduleByFilmId(String filmId);
}
