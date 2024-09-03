package com.project.warmyhomes.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.warmyhomes.entity.concretes.business.*;
import com.project.warmyhomes.entity.concretes.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertResponse {
    private Long id;
    private String title;
    private String description;
    private String slug;
    private BigDecimal price;
    private Integer status;
    private AdvertType advertType;
    private Country country;
    private City city;
    private District district;
    private User user;
    private Category category;
    private List<Image> images;
    private List<CategoryPropertyValue> properties;
    private Boolean isActive;
    private Integer viewCount;
    private String location;
    private List<TourRequest> requests;
}
