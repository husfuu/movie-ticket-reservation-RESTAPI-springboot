package org.binar.movieticketreservation.controller;

import org.binar.movieticketreservation.dto.request.FilmScheduleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ScheduleController {
    ResponseEntity<?> addFilmSchedule(FilmScheduleDto filmScheduleDto);
}
