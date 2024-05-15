package com.project.warmyhomes.entity.concretes.business;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String log;

    @Column(name = "create_at", nullable = false)
    LocalDateTime createAt;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "advert_id")
    Integer advertId;

}
