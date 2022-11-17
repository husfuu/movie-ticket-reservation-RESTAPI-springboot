package org.binar.movieticketreservation.service.serviceimpl;

import java.util.List;

import org.binar.movieticketreservation.dto.FilmServiceInput;
import org.binar.movieticketreservation.dto.FilmServiceOutput;
import org.binar.movieticketreservation.entity.Film;
import org.binar.movieticketreservation.repository.FilmRepository;
import org.binar.movieticketreservation.repository.ScheduleRepository;
import org.binar.movieticketreservation.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(
            ScheduleRepository scheduleRepository,
            FilmRepository filmRepository) {
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
