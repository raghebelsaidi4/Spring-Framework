package com.ragheb.mvc.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "Username should not be empty")
    private String username;
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
