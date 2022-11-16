package org.binar.movieticketreservation.repository;

import java.util.List;

import org.binar.movieticketreservation.dto.FilmServiceOutput;
import org.binar.movieticketreservation.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    @Query(nativeQuery = true)
    List<FilmServiceOutput> getScheduleByFilmId(@Param("filmId") String filmId);
}
