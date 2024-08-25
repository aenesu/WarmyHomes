package com.project.warmyhomes.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

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

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    byte[] data;

    @Column(nullable = false)
    String name;

    String type;

    @Column(nullable = false, columnDefinition = "Boolean default false")
    Boolean featured;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "advert_id", nullable = false)
    Advert advert;
}
