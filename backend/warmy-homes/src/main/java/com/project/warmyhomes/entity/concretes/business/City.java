package com.project.warmyhomes.entity.concretes.business;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 30)
    String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", nullable = false)
    Country country;

}
