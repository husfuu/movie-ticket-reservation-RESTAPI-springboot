package org.binar.movieticketreservation.service;

import org.binar.movieticketreservation.dto.request.FilmScheduleDTO;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    String addSchedule(FilmScheduleDTO filmScheduleDTO);
}
