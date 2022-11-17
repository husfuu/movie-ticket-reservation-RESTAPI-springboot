package org.binar.movieticketreservation.service.serviceimpl;

import org.binar.movieticketreservation.dto.FilmScheduleServiceInput;
import org.binar.movieticketreservation.entity.Film;
import org.binar.movieticketreservation.entity.Schedule;
import org.binar.movieticketreservation.entity.Studio;
import org.binar.movieticketreservation.repository.FilmRepository;
import org.binar.movieticketreservation.repository.ScheduleRepository;
import org.binar.movieticketreservation.repository.StudioRepository;
import org.binar.movieticketreservation.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
    public void addSchedule(FilmScheduleServiceInput filmScheduleServiceInput) throws Exception {
        Film film = filmRepository.findById(filmScheduleServiceInput.getFilmId())
                .orElseThrow(() -> new Exception("film not found"));

        Studio studio = studioRepository.findById(filmScheduleServiceInput.getStudioId())
                .orElseThrow(() -> new Exception("studio not found"));

        Schedule schedule = new Schedule();
        schedule.setFilm(film);
        schedule.setStudio(studio);

        schedule.setStartTime(filmScheduleServiceInput.getStartTime());
        schedule.setEndTime(filmScheduleServiceInput.getEndTime());

        scheduleRepository.save(schedule);
    }
}
