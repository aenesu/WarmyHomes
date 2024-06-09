package com.project.warmyhomes.payload.response.abstracts;

import com.project.warmyhomes.entity.concretes.user.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseUserResponse {
    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private List<Role> roles;
}
