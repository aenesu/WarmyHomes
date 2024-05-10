package com.project.warmyhomes.entity.concretes.business;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private Integer advert_id;

    @Column(nullable = false)
    private LocalDateTime created_at;
}
