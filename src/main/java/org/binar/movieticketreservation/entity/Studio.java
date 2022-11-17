package org.binar.movieticketreservation.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Studio extends BaseEntity {
    @NotNull
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studio", orphanRemoval = true)
    private List<TransactionHistory> transactionHistory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studio", orphanRemoval = true)
    private List<Schedule> schedule;
}
