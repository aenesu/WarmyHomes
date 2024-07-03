package com.project.warmyhomes.payload.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {

    @NotNull(message = "Please enter your reset password code")
    private String code;

    @NotNull(message = "Please enter your password")
    private String password;
}
