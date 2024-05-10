package com.project.warmyhomes.entity.concretes.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category_property_keys")
public class CategoryPropertyKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 200)
    private String name;

    @Column(nullable = false, columnDefinition = "boolean default false") 
    private Boolean built_in;

    @Column(nullable = false)
    private Integer category_id;
}
