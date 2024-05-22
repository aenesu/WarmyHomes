package com.project.warmyhomes.entity.concretes.business;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 30)
    String name;

    @OneToMany(mappedBy = "country")
    Set<Advert> adverts;

    @OneToMany(mappedBy = "country")
    Set<City> cities;

}
