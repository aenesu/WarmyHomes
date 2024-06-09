package com.project.warmyhomes.payload.request.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractUserRequest {

    @NotNull(message = "Please enter your first name")
    @Size(min = 2, max = 30, message = "Your first name must consist of at least 2 characters")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your first name must consist of characters only.")
    private String firstName;

    @NotNull(message = "Please enter your last name")
    @Size(min = 2, max = 30, message = "Your last name must consist of at least 2 characters")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your last name must consist of characters only.")
    private String lastName;

    @NotNull(message = "Please enter your phone number")
    @Size(min = 14, max = 14, message = "Your phone number should be 12 characters long")
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "Please enter valid phone number")
    private String phone;

    @NotNull(message = "Please enter your email address")
    @Size(min = 10, max = 80, message = "Your email address should be between 10 and 80 chars")
    @Email(message = "Please enter valid email address")
    private String email;

}
