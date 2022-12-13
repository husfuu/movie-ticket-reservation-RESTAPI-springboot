package org.binar.movieticketreservation.service.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.binar.movieticketreservation.dto.UserServiceInput;
import org.binar.movieticketreservation.entity.Users;
import org.binar.movieticketreservation.repository.UserRepository;
import org.binar.movieticketreservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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
            newUser.setPassword(passwordEncoder.encode(userServiceInput.getPassword()));

            newUser.setUsername(userServiceInput.getUsername());
            userRepository.save(newUser);
        } catch (Exception e) {
            System.out.println("error service:" + e);

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            log.info("User not found in the database");
        } else {
            log.info("username found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
