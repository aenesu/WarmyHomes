package com.project.warmyhomes.entity.concretes.business;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel; // Add this import statement
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 150)
    String title;

    @Column(length = 300)
    String desc;

    @Column(nullable = false, columnDefinition = "boolean default false")
    boolean builtIn;

    @Column(nullable = false, columnDefinition = "int default 0")
    int viewCount;

    @Column(nullable = false)
    LocalDateTime createAt;

    @Column
    LocalDateTime updateAt;
}