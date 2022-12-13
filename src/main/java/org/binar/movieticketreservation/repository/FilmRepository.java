package org.binar.movieticketreservation.repository;

import java.util.List;

import org.binar.movieticketreservation.dto.response.FilmResponseDTO;
import org.binar.movieticketreservation.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilmRepository extends JpaRepository<Film, String> {
    @Query(nativeQuery = true)
    List<FilmResponseDTO> getAllFilms();
}
