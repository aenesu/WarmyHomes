package com.project.warmyhomes.entity.concretes.user;

import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(min = 2, max = 30)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(min = 10, max = 80)
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Size(min = 8, max = 100)
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "reset_password_code")
    private String resetPasswordCode;

    @Column(name = "built_in", nullable = false)
    private Boolean builtIn;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

}