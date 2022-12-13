package org.binar.movieticketreservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequestDTO {
    @NotBlank(message = "email should not be NULL or EMPTY")
    @Email(message = "email should be valid")
    private String email;
    @NotBlank(message = "password should not be NULL or EMPTY")
    @Size(min = 8, message = "password character should be less than 8 character")
    private String password;
    @NotBlank(message = "username should not be NULL or EMPTY")
    private String username;
}
