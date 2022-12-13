package org.binar.movieticketreservation.service;

import org.binar.movieticketreservation.dto.UserServiceInput;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(UserServiceInput userServiceInput);

    void updateUser(UserServiceInput userServiceInput, String userId) throws Exception;

    void deleteUserById(String userId) throws Exception;
}