package com.project.warmyhomes.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.warmyhomes.entity.concretes.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "create_at", nullable = false)
    LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "advert_id", nullable = false)
    Advert advert;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @PrePersist
    public void prePersistDateTime() {
        ZoneId zoneId = ZoneId.of("US/Eastern");
        LocalDateTime nowDateTime = LocalDateTime.now(zoneId);
        LocalDateTime truncatedDateTime = nowDateTime.withSecond(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = truncatedDateTime.format(formatter);

        createDate = LocalDateTime.parse(formattedDateTime, formatter);
    }
}
