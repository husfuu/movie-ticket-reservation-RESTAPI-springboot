package org.binar.movieticketreservation.repository;

import org.binar.movieticketreservation.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio, String> {
}
