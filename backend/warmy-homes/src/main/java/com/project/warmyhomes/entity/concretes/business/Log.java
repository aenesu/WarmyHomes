package com.project.warmyhomes.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.warmyhomes.entity.concretes.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String log;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "create_at", nullable = false)
    LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "advert_id")
    Advert advert;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
