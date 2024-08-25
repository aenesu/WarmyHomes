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
    String title;
    String description;
    String slug;
    BigDecimal price;
    Integer status;
    AdvertType advertType;
    Country country;
    City city;
    District district;
    User user;
    Category category;
    List<Image> images;
    List<CategoryPropertyValue> properties;
    Boolean isActive;
    Integer viewCount;
    String location;
}
