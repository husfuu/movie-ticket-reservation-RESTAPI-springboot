package org.binar.movieticketreservation.service;

import java.util.List;

import org.binar.movieticketreservation.dto.FilmServiceInput;
import org.binar.movieticketreservation.dto.FilmServiceOutput;
import org.springframework.stereotype.Service;

@Service
public interface FilmService {
    void saveFilm(FilmServiceInput filmServiceInput);

    void updateFilmName(String filmName, String filmId) throws Exception;

    void deleteFilmById(String filmId) throws Exception;

    List<FilmServiceOutput> getAllFilms();

    List<FilmServiceOutput> getScheduleByFilmId(String filmId);
}
