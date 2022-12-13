package org.binar.movieticketreservation.repository;

import org.binar.movieticketreservation.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Users findByUsername(String username);
}
