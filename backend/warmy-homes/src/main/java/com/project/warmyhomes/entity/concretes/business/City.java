package com.project.warmyhomes.entity.concretes.business;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    Country country;

    @OneToMany(mappedBy = "city")
    Set<Advert> adverts;

    @OneToMany(mappedBy = "city")
    Set<District> districts;
}
