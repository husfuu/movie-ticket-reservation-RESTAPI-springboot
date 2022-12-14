package org.binar.movieticketreservation.controller.controllerimpl;

import org.binar.movieticketreservation.controller.ScheduleController;
import org.binar.movieticketreservation.dto.APIResponse;
import org.binar.movieticketreservation.dto.request.FilmScheduleDTO;
import org.binar.movieticketreservation.service.serviceimpl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ScheduleControllerImpl implements ScheduleController {

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    @Operation(
        summary = "Create a new Schedule", 
        description = "Insert a new Schedule into Database", 
        tags = "Schedules",
        security = {@SecurityRequirement(name = "bearer-key")})
    @Override
    @PostMapping(value = "/schedules")
    public ResponseEntity<APIResponse> addFilmSchedule(
            @RequestBody FilmScheduleDTO filmScheduleDto) {
        log.info("ScheduleController::addFilmSchedule success");
        String result = scheduleServiceImpl.addSchedule(filmScheduleDto);

        APIResponse<String> response = APIResponse
                .<String>builder()
                .status("SUCCESS")
                .results(result)
                .build();

        log.info("FilmScheduleController::addFilmSchedule success");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
