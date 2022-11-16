package org.binar.movieticketreservation.controller.controllerimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.binar.movieticketreservation.controller.FilmController;
import org.binar.movieticketreservation.dto.FilmServiceInput;
import org.binar.movieticketreservation.dto.FilmServiceOutput;
import org.binar.movieticketreservation.dto.request.FilmRequestDto;
import org.binar.movieticketreservation.dto.request.FilmUpdateNameRequestDto;
import org.binar.movieticketreservation.service.serviceimpl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FilmControllerImpl implements FilmController {

    @Autowired
    private FilmServiceImpl filmServiceImpl;

    @Override
    @PostMapping(value = "/films")
    public ResponseEntity<?> createFilm(
            @RequestBody FilmRequestDto filmRequestDto) {

        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "create film success");

        try {
            FilmServiceInput filmServiceInput = new FilmServiceInput();
            filmServiceInput.setFilmName(filmRequestDto.getFilm_name());
            filmServiceInput.setOnShow(filmRequestDto.is_on_show());
            // filmServiceInput.setShowTime(filmRequestDto.getShow_time());
            // filmServiceInput.setStartTime(filmRequestDto.getStart_time());
            // filmServiceInput.setEndTime(filmRequestDto.getEnd_time());

            filmServiceImpl.saveFilm(filmServiceInput);

            return new ResponseEntity<>(
                    resp,
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            resp.put("message", "fail to create film : " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/films/{filmId}")
    @Override
    public ResponseEntity<?> updateFilmName(
            @RequestBody FilmUpdateNameRequestDto filmUpdateNameRequestDto,
            @PathVariable("filmId") String filmId) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "update film name success");

        try {
            String filmName = filmUpdateNameRequestDto.getFilmName();
            filmServiceImpl.updateFilmName(filmName, filmId);
            return new ResponseEntity<>(
                    resp,
                    HttpStatus.ACCEPTED);

        } catch (Exception e) {
            resp.put("message", "fail to update film name: " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/films/{filmId}")
    @Override
    public ResponseEntity<?> deleteFilm(@PathVariable("filmId") String filmId) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "delete film success");

        try {
            filmServiceImpl.deleteFilmById(filmId);
            return new ResponseEntity<>(
                    resp,
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            resp.put("message", "fail to delete fillm: " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/films")
    @Override
    public ResponseEntity<?> getAllFilms() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "update film name success");

        try {
            List<FilmServiceOutput> filmSchedules = filmServiceImpl.getAllFilms();
            return new ResponseEntity<>(
                    filmSchedules,
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            resp.put("message", "fail to get film schedules: " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/films/{filmId}")
    @Override
    public ResponseEntity<?> getScheduleByFilmId(@PathVariable("filmId") String filmId) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "get schedule by filmId success");

        try {
            List<FilmServiceOutput> scheduleByFilmId = filmServiceImpl.getScheduleByFilmId(filmId);
            return new ResponseEntity<>(
                    scheduleByFilmId,
                    HttpStatus.ACCEPTED);

        } catch (Exception e) {
            resp.put("message", "fail to get schedule by filmId: " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
