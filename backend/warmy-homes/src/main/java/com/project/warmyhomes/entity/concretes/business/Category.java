package com.project.warmyhomes.entity.concretes.business;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 50)
    private String icon;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean builtIn;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer seq;

    @Column(nullable = false, length = 200)
    private String slug;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    // Getters and setters
}
