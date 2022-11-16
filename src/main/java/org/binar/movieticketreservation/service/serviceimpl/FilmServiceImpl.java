package org.binar.movieticketreservation.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.binar.movieticketreservation.dto.FilmServiceInput;
import org.binar.movieticketreservation.dto.FilmServiceOutput;
import org.binar.movieticketreservation.entity.Film;
import org.binar.movieticketreservation.entity.Schedule;
import org.binar.movieticketreservation.repository.FilmRepository;
import org.binar.movieticketreservation.repository.ScheduleRepository;
import org.binar.movieticketreservation.repository.StudioRepository;
import org.binar.movieticketreservation.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private ScheduleRepository scheduleRepository;
    private FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(
            ScheduleRepository scheduleRepository,
            FilmRepository filmRepository,
            StudioRepository studioRepository) {
        this.scheduleRepository = scheduleRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public void saveFilm(FilmServiceInput filmServiceInput) {
        // tambahin validasi check apakah film udah ada
        Film film = new Film();
        film.setName(filmServiceInput.getFilmName());
        film.setIsOnShow(filmServiceInput.isOnShow());
        filmRepository.save(film);

        Schedule schedule = new Schedule();
        schedule.setFilm(film);
        // schedule.setShowTime(filmServiceInput.getShowTime());
        // schedule.setStartTime(filmServiceInput.getStartTime());
        // schedule.setEndTime(filmServiceInput.getEndTime());
        scheduleRepository.save(schedule);
    }

    @Override
    public void updateFilmName(String filmName, String filmId) throws Exception {
        Film updatedFilm = filmRepository.findById(filmId)
                .orElseThrow(() -> new Exception("film not found"));

        updatedFilm.setName(filmName);
    }

    @Override
    public void deleteFilmById(String filmId) throws Exception {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new Exception("film not found"));

        filmRepository.delete(film);
    }

    @Override
    public List<FilmServiceOutput> getAllFilms() {
        return filmRepository.getAllFilmsAndSchedules();
    }

    @Override
    public List<FilmServiceOutput> getScheduleByFilmId(String filmId) {
        return scheduleRepository.getScheduleByFilmId(filmId);
    }
}
