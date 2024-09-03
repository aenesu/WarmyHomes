package com.project.warmyhomes.payload.mappers;

import com.project.warmyhomes.entity.concretes.business.Advert;
import com.project.warmyhomes.entity.concretes.business.Image;
import com.project.warmyhomes.payload.request.business.AdvertImageRequest;
import com.project.warmyhomes.payload.request.business.AdvertRequest;
import com.project.warmyhomes.payload.response.business.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class AdvertMapper {


    public Advert mapAdvertRequestToAdvert(AdvertRequest advertRequest) {
        return Advert.builder()
                .title(advertRequest.getTitle())
                .description(advertRequest.getDesc())
                .price(advertRequest.getPrice())
                .status(0)
                .builtIn(false)
                .isActive(true)
                .viewCount(0)
                .location(advertRequest.getLocation())
                .build();
    }

    /**
     * @param advert object
     * @return AdvertResponse DTO object
     */
    public AdvertResponse mapAdvertToAdvertResponse(Advert advert) {
        return AdvertResponse.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                .description(advert.getDescription())
                .slug(advert.getSlug())
                .price(advert.getPrice())
                .status(advert.getStatus())
                .advertType(advert.getAdvertType())
                .country(advert.getCountry())
                .city(advert.getCity())
                .district(advert.getDistrict())
                .user(advert.getUser())
                .category(advert.getCategory())
                .images(advert.getImages())
                .properties(advert.getPropertyValues())
                .isActive(advert.getIsActive())
                .viewCount(advert.getViewCount())
                .location(advert.getLocation())
                .advertType(advert.getAdvertType())
                .country(advert.getCountry())
                .city(advert.getCity())
                .district(advert.getDistrict())
                .user(advert.getUser())
                .category(advert.getCategory())
                .properties(advert.getPropertyValues())
                .images(advert.getImages())
                .requests(advert.getRequests())
                .build();
    }

    /**
     * Map an Object[] containing city name and advertisement count to an AdvertCitiesResponse object.
     *
     * @param data an array where data[0] is the city name (String) and data[1] is the advertisement count (Number).
     * @return an AdvertCitiesResponse object with the city name and advertisement count.
     */
    public AdvertCitiesResponse mapAdvertToAdvertCitiesResponse(Object[] data) {
        String city = (String) data[0];
        Long amount = ((Number) data[1]).longValue();

        return AdvertCitiesResponse.builder()
                .city(city)
                .amount(amount)
                .build();
    }

    /**
     * Map an Object[] containing category name and advertisement count to an AdvertCategoriesResponse object.
     *
     * @param data an array where data[0] is the category name (String) and data[1] is the advertisement count (Number).
     * @return an AdvertCategoriesResponse object with the category name and advertisement count.
     */
    public AdvertCategoriesResponse mapAdvertToAdvertCategoriesResponse(Object[] data) {
        String category = (String) data[0];
        Long amount = ((Number) data[1]).longValue();

        return AdvertCategoriesResponse.builder()
                .category(category)
                .amount(amount)
                .build();
    }
}
