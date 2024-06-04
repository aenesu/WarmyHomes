package com.project.warmyhomes.payload.request.abstracts;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseUserRequest extends AbstractUserRequest {

    @NotNull(message = "Please enter your password")
    @Size(min = 8, message = "Your password must consist of at least 8 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Your password must include at least one letter, one number, and one special character")
    private String password;

}
