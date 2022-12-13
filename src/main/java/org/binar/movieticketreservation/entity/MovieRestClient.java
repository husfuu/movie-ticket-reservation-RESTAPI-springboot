package org.binar.movieticketreservation.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MovieRestClient {
    @Value("${movieapi.movie.url}")
    String movieUrl;

    @Value("${movieapi.key.value}")
    String apiKeyValue;
}
