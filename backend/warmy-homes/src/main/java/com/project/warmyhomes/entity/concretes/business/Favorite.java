package com.project.warmyhomes.entity.concretes.business;

import com.project.warmyhomes.entity.concretes.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "create_at", nullable = false)
    LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "advert_id", nullable = false)
    Advert advert;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

}
