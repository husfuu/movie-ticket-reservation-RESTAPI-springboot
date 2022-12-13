package org.binar.movieticketreservation.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import org.binar.movieticketreservation.dto.APIResponse;
import org.binar.movieticketreservation.dto.request.FilmRequestDTO;
import org.binar.movieticketreservation.dto.request.FilmUpdateNameRequestDTO;
import org.binar.movieticketreservation.dto.response.FilmResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FilmControllerTest{
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;

    ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void createFilmTest() throws Exception {
        FilmRequestDTO filmRequestDTO = new FilmRequestDTO();
        filmRequestDTO.setName("The Smile");
        filmRequestDTO.setOverview("The horor movie, fucking scare");
        filmRequestDTO.setTicketPrice(60000.0);
        filmRequestDTO.setVoteAverage(8.0);

        String jsonRequest = om.writeValueAsString(filmRequestDTO);

        MvcResult result = mockMvc.perform(post("/api/v1/films")
            .content(jsonRequest)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        
        APIResponse<FilmResponseDTO> response = om.readValue(resultContent, APIResponse.class);
        Assertions.assertEquals("SUCCESS", response.getStatus());
    }
    
    @Test
    public void updateFilmNameTest() throws Exception{
        FilmUpdateNameRequestDTO filmUpdateNameRequestDTO = new FilmUpdateNameRequestDTO();
        filmUpdateNameRequestDTO.setFilmName("new movie name");
        
        String jsonRequest = om.writeValueAsString(filmUpdateNameRequestDTO);

        MvcResult result = mockMvc.perform(put("/api/v1/films/F02")
            .content(jsonRequest)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isAccepted()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        
        APIResponse<FilmResponseDTO> response = om.readValue(resultContent, 
            APIResponse.class);
        Assertions.assertEquals("SUCCESS", response.getStatus());
    }

    @Test
    public void deleteFilmTest() throws Exception {
        String filmId = "F01";
        
        MvcResult result = mockMvc.perform(delete("/api/v1/films/" + filmId))
            .andExpect(status().isAccepted())
            .andReturn();
        
        String resultContent = result.getResponse().getContentAsString();

        APIResponse<String> response = om.readValue(resultContent, APIResponse.class);

        Assertions.assertEquals("SUCCESS", response.getStatus());
    }

    @Test
    public void getAllFilmsTest() throws Exception{
        MvcResult result = mockMvc.perform(get("/api/v1/films"))
        .andExpect(status().isOk()) 
        .andReturn();

        // APIResponse<List<FilmResponseDTO>> response = 
    }


    @Test
    public void getFilmIdTest() throws Exception{

    }


}



