package org.binar.movieticketreservation.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users extends BaseEntity {
    @NotNull
    @Size(min = 3)
    private String username;

    @NotNull
    private String email;

    @NotNull
    @Size(min = 8)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users", orphanRemoval = true)
    private List<Transaction> transaction;
}
