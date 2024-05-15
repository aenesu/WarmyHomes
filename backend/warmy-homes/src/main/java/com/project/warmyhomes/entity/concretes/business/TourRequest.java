package com.project.warmyhomes.entity.concretes.business;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "tour_requests")
public class TourRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "tour_date", nullable = false)
    LocalDate tourDate;

    @Column(name = "tour_time", nullable = false)
    LocalTime tourTime;

    @Column(nullable = false, columnDefinition = "Integer default 0")
    Integer status;

    @Column(name = "create_at", nullable = false)
    LocalDateTime createAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @Column(name = "advert_id", nullable = false)
    Integer advertId;

    @Column(name = "owner_user_id", nullable = false)
    Integer ownerUserId;

    @Column(name = "guest_user_id", nullable = false)
    Integer guestUserId;
}
