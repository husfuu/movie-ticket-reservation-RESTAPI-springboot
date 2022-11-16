package org.binar.movieticketreservation.service.serviceimpl;

import javax.transaction.Transactional;

import org.binar.movieticketreservation.dto.UserServiceInput;
import org.binar.movieticketreservation.entity.Users;
import org.binar.movieticketreservation.repository.UserRepository;
import org.binar.movieticketreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserServiceInput userServiceInput) {
        // validasi check udah ada user atau belum based email
        try {
            Users newUser = new Users();
            newUser.setEmail(userServiceInput.getEmail());
            newUser.setPassword(userServiceInput.getPassword());
            newUser.setUsername(userServiceInput.getUsername());
            userRepository.save(newUser);
        } catch (Exception e) {
            System.out.println("error servuce:" + e);
            // TODO: handle exception
        }
    }

    @Override
    public void updateUser(UserServiceInput userServiceInput, String userId) throws Exception {
        Users updatedUser = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("not found user"));

        updatedUser.setEmail(userServiceInput.getEmail());
        updatedUser.setUsername(userServiceInput.getUsername());
        updatedUser.setPassword(userServiceInput.getPassword());
    }

    @Override
    public void deleteUserById(String userId) throws Exception {
        Users user = userRepository.findById(userId).orElseThrow(() -> new Exception("user not found"));
        userRepository.delete(user);
    }
}
