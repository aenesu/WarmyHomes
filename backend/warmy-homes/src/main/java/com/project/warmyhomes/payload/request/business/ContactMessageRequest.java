package com.project.warmyhomes.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessageRequest {

    @NotNull(message = "")
    @Size(max = 30, message = "")
    private String firstName;

    @Nullable
    @Size(max = 30, message = "")
    private String lastName;

    @NotNull(message = "")
    @Email(message = "")
    @Size(max = 60, message = "")
    private String email;

    @NotNull(message = "")
    @Size(max = 300, message = "")
    private String message;
}
