package com.project.warmyhomes.payload.request.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequest {

    @NotNull(message = "Please enter your email address")
    @Email(message = "Please enter valid email address")
    private String email;
}
