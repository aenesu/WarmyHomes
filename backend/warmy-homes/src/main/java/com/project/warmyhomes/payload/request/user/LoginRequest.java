package com.project.warmyhomes.payload.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "Email must be not null")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull(message = "Password must be not null")
    private String password;
}
