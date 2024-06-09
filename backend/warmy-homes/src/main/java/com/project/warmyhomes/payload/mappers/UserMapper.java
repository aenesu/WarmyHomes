package com.project.warmyhomes.payload.mappers;

import com.project.warmyhomes.entity.concretes.user.User;
import com.project.warmyhomes.payload.request.abstracts.BaseUserRequest;
import com.project.warmyhomes.payload.response.user.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * @param userRequest DTO
     * @return user from DB
     */
    public User mapUserRequestToUser(BaseUserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .phone(userRequest.getPhone())
                .email(userRequest.getEmail())
                .passwordHash(userRequest.getPassword())
                .builtIn(false)
                .build();
    }

    /**
     * @param user from DB
     * @return UserResponse DTO
     */
    public UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
}
