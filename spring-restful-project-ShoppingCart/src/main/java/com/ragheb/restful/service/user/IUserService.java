package com.ragheb.restful.service.user;

import com.ragheb.restful.dto.UserDto;
import com.ragheb.restful.model.User;
import com.ragheb.restful.request.CreateUserRequest;
import com.ragheb.restful.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);
    UserDto convertUserToDto(User user);

    User getAutenticatedUser();
}
