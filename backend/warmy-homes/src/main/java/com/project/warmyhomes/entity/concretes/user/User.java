package com.project.warmyhomes.entity.concretes.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.warmyhomes.entity.concretes.business.Advert;
import com.project.warmyhomes.entity.concretes.business.Favorite;
import com.project.warmyhomes.entity.concretes.business.Log;
import com.project.warmyhomes.entity.concretes.business.TourRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password_hash", nullable = false)
    String passwordHash;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "reset_password_code")
    String resetPasswordCode;

    @Column(name = "built_in", nullable = false, columnDefinition = "Boolean default false")
    Boolean builtIn;

    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "create_at", nullable = false)
    LocalDateTime createAt;

    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "update_at")
    LocalDateTime updateAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    List<Role> roles;

    @PrePersist
    public void prePersistDateTime() {
        ZoneId zoneId = ZoneId.of("US/Eastern");
        LocalDateTime nowDateTime = LocalDateTime.now(zoneId);
        LocalDateTime truncatedDateTime = nowDateTime.withSecond(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = truncatedDateTime.format(formatter);

        createAt = LocalDateTime.parse(formattedDateTime, formatter);
    }

    @PreUpdate
    public void preUpdateDateTime() {
        ZoneId zoneId = ZoneId.of("US/Eastern");
        LocalDateTime nowDateTime = LocalDateTime.now(zoneId);
        LocalDateTime truncatedDateTime = nowDateTime.withSecond(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = truncatedDateTime.format(formatter);

        updateAt = LocalDateTime.parse(formattedDateTime, formatter);
    }
}
