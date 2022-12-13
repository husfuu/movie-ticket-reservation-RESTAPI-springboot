package org.binar.movieticketreservation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerDoc {
    @Bean
    public OpenAPI apiDoc(@Value("Movie Ticket Reservation Doc") String apiDescription, @Value("v1.0.0") String apiVersion){
        return new OpenAPI()
            .info(new Info()
            .title("Movie Ticket Reservation API")
            .description(apiDescription)
            .version(apiVersion)
            .termsOfService("http://swagger.io/terms")
            .license(new License()
            .name("Apache 2.0")
            .url("http://springdoc.org")))
            .components(new Components()
            .addSecuritySchemes("bearer-key", 
            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
