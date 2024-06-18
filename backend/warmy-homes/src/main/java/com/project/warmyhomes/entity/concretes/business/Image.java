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
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Byte[] data;

    @Column(nullable = false)
    String name;

    String type;

    @Column(nullable = false, columnDefinition = "Boolean default false")
    Boolean featured;

    @ManyToOne
    @JoinColumn(name = "advert_id", nullable = false)
    Advert advert;
}
