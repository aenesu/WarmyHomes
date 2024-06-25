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
@Table(name = "category_property_keys")
public class CategoryPropertyKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 80)
    String name;

    @Column(name = "built_in", nullable = false, columnDefinition = "Boolean default false")
    Boolean builtIn;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;
}
