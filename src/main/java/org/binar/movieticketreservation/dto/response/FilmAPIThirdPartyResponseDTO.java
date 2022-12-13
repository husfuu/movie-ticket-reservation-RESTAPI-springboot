package org.binar.movieticketreservation.dto.response;

import java.util.List;

import org.binar.movieticketreservation.dto.FilmResultAPIThirdPartyDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmAPIThirdPartyResponseDTO {
    private List<FilmResultAPIThirdPartyDTO> results;
}
