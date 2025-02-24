package com.ragheb.mvc.service;

import com.ragheb.mvc.dto.RegistrationDto;
import com.ragheb.mvc.model.User;
import jakarta.validation.constraints.NotEmpty;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);

    User findByUsername(String username);
}
