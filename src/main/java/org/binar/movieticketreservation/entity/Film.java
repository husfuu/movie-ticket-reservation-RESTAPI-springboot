package org.binar.movieticketreservation.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import javax.validation.constraints.NotNull;

import org.binar.movieticketreservation.dto.response.FilmResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SqlResultSetMapping(name = "filmMapping", classes = {
                @ConstructorResult(targetClass = FilmResponseDTO.class, columns = {
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "overview", type = String.class),
                                @ColumnResult(name = "vote_average", type = Double.class),
                                @ColumnResult(name = "ticket_price", type = Double.class),
                                @ColumnResult(name = "is_on_show", type = Boolean.class)
                })
})
// @NamedNativeQuery(name = "Film.getAllFilmsAndSchedules", query = "select\n" +
// "f.name as film_name,\n" +
// "overview,\n" +
// "vote_average,\n" +
// "st.name as studio_name\n" +
// "from schedule sc\n" +
// "inner join film f on sc.film_id = f.id\n" +
// "inner join studio st on sc.studio_id = st.id\n" +
// "where f.is_on_show = true", resultSetMapping = "filmMapping", resultClass =
// FilmResponseDTO.class)

@NamedNativeQuery(name = "Film.getAllFilms", query = "select name,overview,vote_average,ticket_price,is_on_show from film f;", resultSetMapping = "filmMapping", resultClass = FilmResponseDTO.class)

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film extends BaseEntity {
        @NotNull
        private String name;

        @NotNull
        private String overview;

        @NotNull
        private double voteAverage;

        @NotNull
        private Double ticketPrice;

        @NotNull
        private Boolean isOnShow;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "film", orphanRemoval = true)
        private List<Schedule> schedules;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "film", orphanRemoval = true)
        private List<Transaction> transaction;
}
