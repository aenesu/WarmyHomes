package com.project.warmyhomes.payload.mappers;

import com.project.warmyhomes.entity.concretes.business.Advert;
import com.project.warmyhomes.entity.concretes.business.Image;
import com.project.warmyhomes.payload.request.business.AdvertImageRequest;
import com.project.warmyhomes.payload.request.business.AdvertRequest;
import com.project.warmyhomes.payload.response.business.AdvertPropertyResponse;
import com.project.warmyhomes.payload.response.business.AdvertResponse;
import com.project.warmyhomes.payload.response.business.ImageResponse;
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
                .build();
    }

}
