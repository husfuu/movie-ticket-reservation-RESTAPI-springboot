package org.binar.movieticketreservation.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import javax.validation.constraints.NotNull;

import org.binar.movieticketreservation.dto.FilmServiceOutput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SqlResultSetMapping(name = "filmMapping", classes = {
                @ConstructorResult(targetClass = FilmServiceOutput.class, columns = {
                                @ColumnResult(name = "film_name", type = String.class),
                                @ColumnResult(name = "show_time", type = LocalDateTime.class),
                                @ColumnResult(name = "start_time", type = LocalDateTime.class),
                                @ColumnResult(name = "end_time", type = LocalDateTime.class),
                                @ColumnResult(name = "studio_name", type = String.class)
                })
})
@NamedNativeQuery(name = "Film.getAllFilmsAndSchedules", query = "select\n" +
                "f.name as film_name,\n" +
                "show_time,\n" +
                "start_time,\n" +
                "end_time,\n" +
                "st.name as studio_name\n" +
                "from schedule sc\n" +
                "inner join film f on sc.film_id = f.id\n" +
                "inner join studio st on sc.studio_id = st.id\n" +
                "where f.is_on_show = true", resultSetMapping = "filmMapping", resultClass = FilmServiceOutput.class)

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film extends BaseEntity {
        @NotNull
        private String name;

        @NotNull
        private Boolean isOnShow;

        @NotNull
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "film", orphanRemoval = true)
        private List<Schedule> schedules;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "film", orphanRemoval = true)
        private List<TransactionHistory> transactionHistory;
}
