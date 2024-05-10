package com.project.warmyhomes.entity.concretes.business;

import javax.persistence.*;

@Entity
@Table(name = "category_property_values")
public class CategoryPropertyValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String value;

    @Column(nullable = false)
    private Integer advert_id;

    @Column(nullable = false)
    private Integer category_property_key_id;

    // Getters and setters
}