package com.project.warmyhomes.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "US")
    @Column(name = "update_at")
    LocalDateTime updateDate;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<CategoryPropertyKey> properties;


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
