package org.binar.movieticketreservation.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.validation.constraints.NotNull;

import org.binar.movieticketreservation.dto.response.FilmAndScheduleResponseDTO;
import org.binar.movieticketreservation.dto.response.FilmResponseDTO;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SqlResultSetMapping(name = "scheduleMapping", classes = {
                @ConstructorResult(targetClass = FilmAndScheduleResponseDTO.class, columns = {
                                @ColumnResult(name = "film_name", type = String.class),
                                @ColumnResult(name = "start_time", type = LocalDateTime.class),
                                @ColumnResult(name = "end_time", type = LocalDateTime.class),
                                @ColumnResult(name = "studio_name", type = String.class)
                })
})
@NamedNativeQuery(name = "Schedule.getScheduleByFilmId", query = "select\n" +
                "f.name as film_name,\n" +
                "start_time,\n" +
                "end_time,\n" +
                "st.name as studio_name\n" +
                "from schedule sc\n" +
                "inner join film f on sc.film_id = f.id\n" +
                "inner join studio st on sc.studio_id = st.id\n" +
                "where f.is_on_show = true and sc.film_id = ?1", resultSetMapping = "scheduleMapping", resultClass = FilmResponseDTO.class)

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule extends BaseEntity {
        @NotNull
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "film_id")
        private Film film;

        @NotNull
        @ManyToOne
        @JoinColumn(name = "studio_id")
        private Studio studio;

        private LocalDateTime startTime;

        private LocalDateTime endTime;
}
