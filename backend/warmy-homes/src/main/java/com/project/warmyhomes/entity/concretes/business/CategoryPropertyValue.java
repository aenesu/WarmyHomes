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
@Table(name = "category_property_values")
public class CategoryPropertyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    String value;

    @ManyToOne
    @JoinColumn(name = "advert_id", nullable = true)
    Advert advert;

    @OneToOne
    @JoinColumn(name = "category_property_key_id", nullable = true)
    CategoryPropertyKey propertyKey;
}