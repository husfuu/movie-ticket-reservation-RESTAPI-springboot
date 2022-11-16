package org.binar.movieticketreservation.controller;

import org.binar.movieticketreservation.dto.request.UserRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface UserController {
    ResponseEntity<?> createUser(UserRequestDto userRequestDto);

    ResponseEntity<?> updateUser(UserRequestDto userRequestDto, String userId);

    ResponseEntity<?> deleteUser(String userId);
}
