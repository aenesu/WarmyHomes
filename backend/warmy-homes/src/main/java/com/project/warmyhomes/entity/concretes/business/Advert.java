package com.project.warmyhomes.entity.concretes.business;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import com.project.warmyhomes.entity.concretes.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "adverts")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 150)
    String title;

    @Column(length = 300)
    String description;

    @Column(nullable = false, length = 200)
    String slug;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal price;

    @Column(nullable = false, columnDefinition = "Integer default 0")
    Integer status;

    @Column(name = "built_in", nullable = false, columnDefinition = "Boolean default false")
    Boolean builtIn;

    @Column(name = "is_active", nullable = false, columnDefinition = "Boolean default true")
    Boolean isActive;

    @Column(name = "view_count", nullable = false, columnDefinition = "Integer default 0")
    Integer viewCount;

    String location;

    @Column(name = "create_at", nullable = false)
    LocalDateTime createAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "advert_type_id", nullable = false)
    AdvertType advertType;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    Country country;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    City city;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    District district;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @OneToMany(mappedBy = "advert")
    Set<CategoryPropertyValue> propertyValues;

    @OneToMany(mappedBy = "advert")
    Set<Favorite> favorites;

    @OneToMany(mappedBy = "advert")
    Set<Image> images;

    @OneToMany(mappedBy = "advert")
    Set<Log> logs;

    @OneToMany(mappedBy = "advert")
    Set<TourRequest> tourRequests;

}