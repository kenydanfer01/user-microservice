package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Pattern(regexp = "\\d{1,10}", message = "DNI must be numeric and have a maximum length of 10 characters")
    private String dni;

    @NotBlank
    @Pattern(regexp = "^\\+?\\d{1,12}$", message = "Phone number must be a sequence of up to 13 digits, optionally starting with a '+' sign")
    private String phone;

    @Past(message = "Date of birth must be in the past")
    private LocalDate birthday;

    @NotBlank
    @Email(message = "Invalid email format")
    private String mail;

    @NotBlank
    private String password;

    @JsonIgnore
    private Long idRole;

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }
}
