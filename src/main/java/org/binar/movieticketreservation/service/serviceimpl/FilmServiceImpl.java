package org.binar.movieticketreservation.service.serviceimpl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.binar.movieticketreservation.dto.response.FilmAndScheduleResponseDTO;
import org.binar.movieticketreservation.dto.response.FilmResponseDTO;
import org.binar.movieticketreservation.dto.request.FilmRequestDTO;
import org.binar.movieticketreservation.entity.Film;
import org.binar.movieticketreservation.exception.FilmServiceException;
import org.binar.movieticketreservation.helper.ValueMapper;
import org.binar.movieticketreservation.repository.FilmRepository;
import org.binar.movieticketreservation.repository.ScheduleRepository;
import org.binar.movieticketreservation.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class FilmServiceImpl implements FilmService {

    @Autowired
    private final ScheduleRepository scheduleRepository;

    @Autowired
    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(
            ScheduleRepository scheduleRepository,
            FilmRepository filmRepository) {
        this.scheduleRepository = scheduleRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public FilmResponseDTO saveFilm(FilmRequestDTO filmRequestDTO) {
        try {
            Film film = new Film();
            film.setName(filmRequestDTO.getName());
            film.setTicketPrice(filmRequestDTO.getTicketPrice());
            film.setVoteAverage(filmRequestDTO.getVoteAverage());
            film.setOverview(filmRequestDTO.getOverview());
            film.setIsOnShow(filmRequestDTO.isOnShow());
            Film filmSaved = filmRepository.save(film);
            System.out.println("film saved: " + filmSaved);
            FilmResponseDTO filmResponseDTO = ValueMapper.convertToFilmResponseDTO(filmSaved);
            log.debug("FilmService: createFilm success");
            return filmResponseDTO;
        } catch (Exception exception) {
            log.debug("Exception occurred while persisting film to database , Exception message {}",
                    exception.getMessage());
            throw new FilmServiceException("Exception occurred while creating film");
        }
    }

    @Override
    public FilmResponseDTO saveFilmProvided(FilmRequestDTO filmRequestDTO) {
        try {
            Film film = new Film();
            film.setName(filmRequestDTO.getName());
            film.setOverview(filmRequestDTO.getOverview());
            film.setVoteAverage(filmRequestDTO.getVoteAverage());
            film.setTicketPrice(filmRequestDTO.getTicketPrice());
            film.setIsOnShow(filmRequestDTO.isOnShow());
            Film filmSaved = filmRepository.save(film);
            FilmResponseDTO filmResponseDTO = ValueMapper.convertToFilmResponseDTO(filmSaved);
            log.debug("FilmService: createFilm success");
            return filmResponseDTO;
        } catch (Exception exception) {
            log.debug("Exception occurred while persisting film to database , Exception message {}",
                    exception.getMessage());
            throw new FilmServiceException("Exception occurred while creating film");
        }
    }

    @Override
    public FilmResponseDTO updateFilmName(String filmName, String filmId) throws Exception {
        try {
            Film updatedFilm = filmRepository.findById(filmId)
                    .orElseThrow(() -> new Exception("film not found"));
            updatedFilm.setName(filmName);
            log.debug("FilmService: updateFilmName success");
            FilmResponseDTO filmResponseDTO = new FilmResponseDTO();
            filmResponseDTO.setName(filmName);
            return filmResponseDTO;
        } catch (Exception exception) {
            log.debug("Exception occurred while persisting film to database , Exception message {}",
                    exception.getMessage());
            throw new FilmServiceException("Exception occurred while update film status");
        }
    }

    @Override
    public String deleteFilmById(String filmId) throws Exception {
        try {
            Film film = filmRepository.findById(filmId)
                    .orElseThrow(() -> new Exception("film not found"));
            filmRepository.delete(film);
            return "success to delete film";
        } catch (Exception exception) {
            log.debug("Exception occurred while persisting film to database , Exception message {}",
                    exception.getMessage());
            throw new FilmServiceException("Exception occurred while delete film");
        }
    }

    @Override
    public List<FilmResponseDTO> getAllFilms() {
        try {
            List<FilmResponseDTO> films = filmRepository.getAllFilms();
            return films;
        } catch (Exception exception) {
            log.debug("Exception occurred while persisting film to database , Exception message {}",
                    exception.getMessage());
            // System.out.println("ERRORRRRR: {} " + exception.getMessage());
            throw new FilmServiceException("Exception occurred while received all films");
        }
    }

    @Override
    public List<FilmAndScheduleResponseDTO> getScheduleByFilmId(String filmId) {
        try {
            System.out.println("filmId: " + filmId);
            List<FilmAndScheduleResponseDTO> films = scheduleRepository.getScheduleByFilmId(filmId);
            System.out.println("hasillllll: " + scheduleRepository.getScheduleByFilmId(filmId));
            System.out.println(films);
            return scheduleRepository.getScheduleByFilmId(filmId);
        } catch (Exception exception) {
            System.out.println("ini ERROOOOORR BANGGGG" + exception.getMessage());
            log.debug("Exception occurred while persisting film to database , Exception message {}",
                    exception.getMessage());
            throw new FilmServiceException("Exception occurred while received film by Id");
        }
    }
}
