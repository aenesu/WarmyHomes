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
@Table(name = "districts")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 30)
    String name;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    City city;

    @OneToMany(mappedBy = "district")
    Set<Advert> adverts;
}
