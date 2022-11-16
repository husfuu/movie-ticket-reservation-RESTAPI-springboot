package org.binar.movieticketreservation.service;

import java.util.List;

import org.binar.movieticketreservation.dto.FilmServiceOutput;
import org.binar.movieticketreservation.service.serviceimpl.FilmServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FilmServiceTest {
    @Autowired
    private FilmServiceImpl filmServiceiImpl;

    @Test
    public void A_GetFilmsAndSchedules() {
        List<FilmServiceOutput> result = filmServiceiImpl.getAllFilms();
        System.out.println(result);
    }

    @Test
    public void B_GetScheduleByFilmId() {
        List<FilmServiceOutput> result = filmServiceiImpl.getScheduleByFilmId("F02");
        System.out.println(result);
    }

}
