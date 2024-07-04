package com.project.warmyhomes.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessageRequest {

    @NotNull(message = "Please enter first name")
    @Size(max = 30, message = "Your first name should be at most 30 chars")
    private String firstName;

    @NotNull(message = "Please enter last name")
    @Size(max = 30, message = "Your last name should be at most 30 chars")
    private String lastName;

    @NotNull(message = "Please enter your email")
    @Size(max = 60, message = "Your email should be at most 60 chars")
    @Email(message = "Please enter valid email")
    private String email;

    @NotNull(message = "Please enter message")
    @Size(max = 300, message = "Your message should be at most 300 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your message must consist of the character .")
    private String message;
}
