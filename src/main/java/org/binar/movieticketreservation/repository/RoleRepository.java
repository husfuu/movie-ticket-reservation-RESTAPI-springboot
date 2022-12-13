package org.binar.movieticketreservation.repository;

import org.binar.movieticketreservation.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);
}
