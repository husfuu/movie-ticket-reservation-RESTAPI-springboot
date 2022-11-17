package org.binar.movieticketreservation.service;

import org.binar.movieticketreservation.dto.FilmScheduleServiceInput;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    void addSchedule(FilmScheduleServiceInput filmScheduleServiceInput) throws Exception;
}
