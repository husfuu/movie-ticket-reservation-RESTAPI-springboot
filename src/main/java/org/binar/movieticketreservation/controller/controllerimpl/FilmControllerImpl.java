package org.binar.movieticketreservation.controller.controllerimpl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.binar.movieticketreservation.controller.FilmController;
import org.binar.movieticketreservation.dto.APIResponse;
import org.binar.movieticketreservation.dto.FilmResultAPIThirdPartyDTO;
import org.binar.movieticketreservation.dto.response.FilmResponseDTO;
import org.binar.movieticketreservation.dto.request.FilmProvidedRequestDTO;
import org.binar.movieticketreservation.dto.request.FilmRequestDTO;
import org.binar.movieticketreservation.dto.request.FilmUpdateNameRequestDTO;
import org.binar.movieticketreservation.dto.response.FilmAPIThirdPartyResponseDTO;
import org.binar.movieticketreservation.dto.response.FilmAndScheduleResponseDTO;
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
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class FilmControllerImpl implements FilmController {

        @Autowired
        private FilmServiceImpl filmServiceImpl;

        @Autowired
        private RestTemplate restTemplate;

        @Operation(
                summary = "Create new Film", 
                description = "Insert a new Film into Database", 
                tags = "Films", 
                security = {@SecurityRequirement(name = "bearer-key")})

        @Override
        @PostMapping(value = "/films")
        public ResponseEntity<APIResponse> createFilm(
                        @RequestBody @Valid FilmRequestDTO filmRequestDto) {
                log.info("FilmController::createFilm request body");

                FilmResponseDTO filmResponseDTO = filmServiceImpl.saveFilm(filmRequestDto);

                APIResponse<FilmResponseDTO> responseDTO = APIResponse
                                .<FilmResponseDTO>builder()
                                .status("SUCCESS")
                                .results(filmResponseDTO)
                                .build();
                
                System.out.println("ini response dto: " + responseDTO);

                log.info("FilmController::createFilm success");
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }


        @Operation(
                summary = "Create new Film based on upcoming films", 
                description = "Insert a new Film that get from upcoming films into Database", 
                tags = "Films", 
                security = {@SecurityRequirement(name = "bearer-key")})

        @Override
        @PostMapping(value = "/films_provided")
        public ResponseEntity<APIResponse> addFilmProvided(
                        @RequestBody @Valid FilmProvidedRequestDTO filmProvidedRequestDto) {
                log.info("FilmController::createFilm request body");

                Long filmId = filmProvidedRequestDto.getFilmId(); // ini buang aja, id generated from uuid
                Double ticketPrice = filmProvidedRequestDto.getTicketPrice();

                FilmResultAPIThirdPartyDTO filmResultAPIThirdPartyDTO = restTemplate.getForObject(
                                "https://api.themoviedb.org/3/movie/" + filmId
                                                + "?api_key=aef006e56c9f8dc32941a7503504da89&language=en-US",
                                FilmResultAPIThirdPartyDTO.class);

                FilmRequestDTO filmRequestDTO = new FilmRequestDTO();
                filmRequestDTO.setName(filmResultAPIThirdPartyDTO.getTitle());
                filmRequestDTO.setVoteAverage(filmResultAPIThirdPartyDTO.getVote_average());
                filmRequestDTO.setOverview(filmResultAPIThirdPartyDTO.getOverview());
                filmRequestDTO.setTicketPrice(ticketPrice);

                FilmResponseDTO filmResponseDTO = filmServiceImpl.saveFilmProvided(filmRequestDTO);

                APIResponse<FilmResponseDTO> responseDTO = APIResponse
                                .<FilmResponseDTO>builder()
                                .status("SUCCESS")
                                .results(filmResponseDTO)
                                .build();

                log.info("FilmController::createFilm success");
                return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }

        @Operation(
                summary = "Update Film by Id", 
                description = "Update Film by id", 
                tags = "Films", 
                security = {@SecurityRequirement(name = "bearer-key")})

        @PutMapping(value = "/films/{filmId}")
        @Override
        public ResponseEntity<APIResponse> updateFilmName(
                        @RequestBody FilmUpdateNameRequestDTO filmUpdateNameRequestDto,
                        @PathVariable("filmId") String filmId) throws Exception {
                log.info("FilmController::updateFilm request body");
                FilmResponseDTO filmName = filmServiceImpl.updateFilmName(filmUpdateNameRequestDto.getFilmName(),
                                filmId);

                APIResponse<FilmResponseDTO> responseDTO = APIResponse
                                .<FilmResponseDTO>builder()
                                .status("SUCCESS")
                                .results(filmName)
                                .build();

                log.info("FilmController::updateFilm success");
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        }

        @Operation(
                summary = "Delete Film by Id", 
                description = "Delete Film by id", 
                tags = "Films", 
                security = {@SecurityRequirement(name = "bearer-key")})
        @DeleteMapping(value = "/films/{filmId}")
        @Override
        public ResponseEntity<APIResponse> deleteFilm(@PathVariable("filmId") String filmId) throws Exception {
                String message = filmServiceImpl.deleteFilmById(filmId);
                APIResponse<String> responseDTO = APIResponse
                                .<String>builder()
                                .status("SUCCESS")
                                .results(message)
                                .build();

                log.info("FilmController::deletedFilm success");
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        }

        @Operation(
                summary = "Get all films", 
                description = "Get a list of Films", 
                tags = "Films", 
                security = {@SecurityRequirement(name = "bearer-key")})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Found the Films", 
                        content = {
                                @Content(
                                        mediaType = "application/json",
                                        array = @ArraySchema(schema = @Schema(implementation = FilmResponseDTO.class)))}),
                @ApiResponse(responseCode = "404", description = "Films is not found", content = @Content)}
        )

        @GetMapping(value = "/films")
        @Override
        public ResponseEntity<APIResponse> getAllFilms() {
                List<FilmResponseDTO> films = filmServiceImpl.getAllFilms();

                APIResponse<List<FilmResponseDTO>> responseDTO = APIResponse
                                .<List<FilmResponseDTO>>builder()
                                .status("SUCCESS")
                                .results(films)
                                .build();

                log.info("FilmController::getAllFilms success");
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }

        @Operation(
                summary = "Get film by id", 
                description = "Get a film based on id", 
                tags = "Films", 
                security = {@SecurityRequirement(name = "bearer-key")})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Found the Films", 
                        content = {
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = FilmResponseDTO.class))}),
                @ApiResponse(responseCode = "404", description = "Film is not found", content = @Content)}
        )

        @GetMapping(value = "/films/{filmId}")
        @Override
        public ResponseEntity<APIResponse> getScheduleByFilmId(@PathVariable("filmId") String filmId) {
                List<FilmAndScheduleResponseDTO> scheduleFilms = filmServiceImpl.getScheduleByFilmId(filmId);

                APIResponse<List<FilmAndScheduleResponseDTO>> responseDTO = APIResponse
                                .<List<FilmAndScheduleResponseDTO>>builder()
                                .status("SUCCESS")
                                .results(scheduleFilms)
                                .build();

                log.info("FilmController::getScheduleByFilmId success");
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }

        @Operation(
                summary = "Get all upcomings films", 
                description = "Get upcomming films based on The Movie Database API | Third Party API", 
                tags = "Films", 
                security = {@SecurityRequirement(name = "bearer-key")})
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Found the Films", 
                        content = {
                                @Content(
                                        mediaType = "application/json",
                                        array = @ArraySchema(schema = @Schema(implementation = FilmResultAPIThirdPartyDTO.class)))}),
                @ApiResponse(responseCode = "404", description = "Films is not found", content = @Content)}
        )

        @GetMapping(value = "/upcoming")
        @Override
        public ResponseEntity<APIResponse> getAllUpcomingFilms() {
                try {
                        FilmAPIThirdPartyResponseDTO responseEntity = restTemplate.getForObject(
                                        "https://api.themoviedb.org/3/movie/upcoming?api_key=aef006e56c9f8dc32941a7503504da89&language=en-US&page=1",
                                        FilmAPIThirdPartyResponseDTO.class);

                        List<FilmResultAPIThirdPartyDTO> res = responseEntity.getResults();

                        APIResponse<List<FilmResultAPIThirdPartyDTO>> responseDTO = APIResponse
                                        .<List<FilmResultAPIThirdPartyDTO>>builder()
                                        .status("SUCCESS")
                                        .results(res)
                                        .build();
                        log.info("FilmController::getAllUpcomingFilms success");
                        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
                } catch (Exception exception) {
                        log.debug("Exception occurred while getAllUpcomingFilms from Third Party API, Exception message {}",
                                        exception.getMessage());
                        throw exception;
                }
        }
}