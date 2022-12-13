package org.binar.movieticketreservation.service.serviceimpl;

import lombok.extern.slf4j.Slf4j;

import org.binar.movieticketreservation.dto.request.FilmScheduleDTO;
import org.binar.movieticketreservation.entity.Film;
import org.binar.movieticketreservation.entity.Schedule;
import org.binar.movieticketreservation.entity.Studio;
import org.binar.movieticketreservation.exception.ScheduleServiceException;
import org.binar.movieticketreservation.repository.FilmRepository;
import org.binar.movieticketreservation.repository.ScheduleRepository;
import org.binar.movieticketreservation.repository.StudioRepository;
import org.binar.movieticketreservation.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;
    private FilmRepository filmRepository;
    private StudioRepository studioRepository;

    @Autowired
    public ScheduleServiceImpl(
            ScheduleRepository scheduleRepository,
            FilmRepository filmRepository,
            StudioRepository studioRepository) {
        this.scheduleRepository = scheduleRepository;
        this.filmRepository = filmRepository;
        this.studioRepository = studioRepository;
    }

    @Override
    public String addSchedule(FilmScheduleDTO filmScheduleDTO) {
        try {
            Film film = filmRepository.findById(filmScheduleDTO.getFilmId())
                    .orElseThrow(() -> new Exception("film not found"));

            Studio studio = studioRepository.findById(filmScheduleDTO.getStudioId())
                    .orElseThrow(() -> new Exception("studio not found"));

            Schedule schedule = new Schedule();
            schedule.setFilm(film);
            schedule.setStudio(studio);

            schedule.setStartTime(filmScheduleDTO.getStartTime());
            schedule.setEndTime(filmScheduleDTO.getEndTime());

            scheduleRepository.save(schedule);
            log.debug("success to add schedule film");
            return "success to add schedule film";
        } catch (Exception e) {
            log.error("Exception occured while create schedule", e.getMessage());
            throw new ScheduleServiceException("Exception occured while create schedule");
        }
    }
}
