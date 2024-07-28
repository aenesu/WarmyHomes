package com.project.warmyhomes.entity.concretes.business;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.warmyhomes.entity.concretes.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "create_at", nullable = false)
    LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "update_at")
    LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "advert_type_id", nullable = false)
    AdvertType advertType;

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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @OneToMany(mappedBy = "advert", cascade = CascadeType.ALL)
    private List<CategoryPropertyValue> propertyValues;

    @PrePersist
    public void prePersistDateTime() {
        ZoneId zoneId = ZoneId.of("US/Eastern");
        LocalDateTime nowDateTime = LocalDateTime.now(zoneId);
        LocalDateTime truncatedDateTime = nowDateTime.withSecond(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = truncatedDateTime.format(formatter);

        createDate = LocalDateTime.parse(formattedDateTime, formatter);
    }

    @PreUpdate
    public void preUpdateDateTime() {
        ZoneId zoneId = ZoneId.of("US/Eastern");
        LocalDateTime nowDateTime = LocalDateTime.now(zoneId);
        LocalDateTime truncatedDateTime = nowDateTime.withSecond(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = truncatedDateTime.format(formatter);

        updateDate = LocalDateTime.parse(formattedDateTime, formatter);
    }
}