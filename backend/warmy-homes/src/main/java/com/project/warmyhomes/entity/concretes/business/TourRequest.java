package com.project.warmyhomes.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.warmyhomes.entity.concretes.user.User;
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
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "tour_requests")
public class TourRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "tour_date", nullable = false)
    LocalDate tourDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm",timezone = "US")
    @Column(name = "tour_time", nullable = false)
    LocalTime tourTime;

    @Column(nullable = false, columnDefinition = "Integer default 0")
    Integer status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "create_at", nullable = false)
    LocalDateTime createAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "update_at")
    LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "advert_id", nullable = false)
    Advert advert;

    @ManyToOne
    @JoinColumn(name = "owner_user_id", nullable = false)
    User owner;

    @ManyToOne
    @JoinColumn(name = "guest_user_id", nullable = false)
    User guest;
}
