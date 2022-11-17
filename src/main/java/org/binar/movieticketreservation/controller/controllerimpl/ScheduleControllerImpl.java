package org.binar.movieticketreservation.controller.controllerimpl;

import java.util.HashMap;
import java.util.Map;

import org.binar.movieticketreservation.controller.ScheduleController;
import org.binar.movieticketreservation.dto.FilmScheduleServiceInput;
import org.binar.movieticketreservation.dto.request.FilmScheduleDto;
import org.binar.movieticketreservation.service.serviceimpl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ScheduleControllerImpl implements ScheduleController {

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    @Override
    @PostMapping(value = "/schedules")
    public ResponseEntity<?> addFilmSchedule(
            @RequestBody FilmScheduleDto filmScheduleDto) {

        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "create film success");

        try {
            FilmScheduleServiceInput filmScheduleServiceInput = new FilmScheduleServiceInput();
            filmScheduleServiceInput.setStartTime(filmScheduleDto.getStartTime());
            filmScheduleServiceInput.setEndTime(filmScheduleDto.getEndTime());
            filmScheduleServiceInput.setFilmId(filmScheduleDto.getFilmId());
            filmScheduleServiceInput.setStudioId(filmScheduleDto.getStudioId());

            scheduleServiceImpl.addSchedule(filmScheduleServiceInput);

            return new ResponseEntity<>(
                    resp,
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            resp.put("message", "fail to create film schedule: " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
