package com.project.warmyhomes.entity.concretes.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name", nullable = false, length = 30)
    String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    String lastName;

    @Column(nullable = false, unique = true, length = 80)
    String email;

    @Column(nullable = false, unique = true)
    String phone;

    @Column(name = "password_hash", nullable = false)
    String passwordHash;

    @Column(name = "reset_password_code")
    String resetPasswordCode;

    @Column(name = "built_in", nullable = false, columnDefinition = "Boolean default false")
    Boolean builtIn;

    @Column(name = "create_at", nullable = false)
    LocalDateTime createAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;
    
}