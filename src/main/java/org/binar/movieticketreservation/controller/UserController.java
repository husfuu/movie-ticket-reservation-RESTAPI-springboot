package org.binar.movieticketreservation.controller;

import org.binar.movieticketreservation.dto.request.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserController {
    ResponseEntity<?> createUser(UserRequestDTO userRequestDto);

    ResponseEntity<?> updateUser(UserRequestDTO userRequestDto, String userId);

    ResponseEntity<?> deleteUser(String userId);
}
