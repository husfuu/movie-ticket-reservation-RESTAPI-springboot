package org.binar.movieticketreservation.controller;

import org.binar.movieticketreservation.dto.APIResponse;
import org.binar.movieticketreservation.dto.request.FilmScheduleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ScheduleController {
    ResponseEntity<APIResponse> addFilmSchedule(FilmScheduleDTO filmScheduleDto);
}
