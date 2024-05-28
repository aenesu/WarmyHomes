package com.project.warmyhomes.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 150)
    String title;

    @Column(nullable = false, length = 50)
    String icon;

    @Column(name = "built_in", nullable = false, columnDefinition = "Boolean default false")
    Boolean builtIn;

    @Column(nullable = false, columnDefinition = "Integer default 0")
    Integer seq;

    @Column(nullable = false, length = 200)
    String slug;

    @Column(name = "is_active", nullable = false, columnDefinition = "Boolean default true")
    Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "create_at", nullable = false)
    LocalDateTime createAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "update_at")
    LocalDateTime updateAt;

    @OneToMany(mappedBy = "category")
    Set<Advert> adverts;

    @OneToMany(mappedBy = "category")
    Set<CategoryPropertyKey> propertyKeys;

}
