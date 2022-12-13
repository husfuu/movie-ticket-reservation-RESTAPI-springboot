package org.binar.movieticketreservation.controller.controllerimpl;

import java.util.HashMap;
import java.util.Map;

import org.binar.movieticketreservation.controller.UserController;
import org.binar.movieticketreservation.dto.UserServiceInput;
import org.binar.movieticketreservation.dto.request.UserRequestDTO;
import org.binar.movieticketreservation.service.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Operation(
        summary = "Create a new User", 
        description = "Insert a new User into Database | Registration Process", 
        tags = "Users")
    @Override
    @PostMapping(value = "/users")
    public ResponseEntity<?> createUser(
            @RequestBody UserRequestDTO userRequestDto) {

        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "create user success");

        try {
            UserServiceInput userServiceInput = new UserServiceInput();
            userServiceInput.setUsername(userRequestDto.getUsername());
            userServiceInput.setEmail(userRequestDto.getEmail());
            userServiceInput.setPassword(userRequestDto.getPassword());

            userServiceImpl.saveUser(userServiceInput);

            return new ResponseEntity<>(
                    resp,
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            resp.put("message", "fail to create user : " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
        summary = "Update User by id", 
        description = "Update User by id", 
        tags = "Users", 
        security = {@SecurityRequirement(name = "bearer-key")})
    @PutMapping(value = "/users/{userId}")
    @Override
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDTO userRequestDto,
            @PathVariable("userId") String userId) {

        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "update user success");

        try {
            UserServiceInput userServiceInput = new UserServiceInput();
            userServiceInput.setEmail(userRequestDto.getEmail());
            userServiceInput.setUsername(userRequestDto.getUsername());
            userServiceInput.setPassword(userRequestDto.getPassword());

            userServiceImpl.updateUser(userServiceInput, userId);
            return new ResponseEntity<>(
                    resp,
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            resp.put("message", "fail to update user : " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
        summary = "Delete User by id", 
        description = "Delete User by id", 
        tags = "Users", 
        security = {@SecurityRequirement(name = "bearer-key")})

    @DeleteMapping(value = "/users/{userId}")
    @Override
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) {

        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "delete user success");

        try {
            userServiceImpl.deleteUserById(userId);
            return new ResponseEntity<>(
                    resp,
                    HttpStatus.ACCEPTED);
        } catch (Exception e) {
            resp.put("message", "fail to delete user: " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
