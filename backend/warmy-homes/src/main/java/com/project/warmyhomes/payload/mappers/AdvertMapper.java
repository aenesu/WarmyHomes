package com.project.warmyhomes.payload.mappers;

import com.project.warmyhomes.entity.concretes.business.Advert;
import com.project.warmyhomes.payload.response.business.AdvertResponse;
import org.springframework.stereotype.Component;

@Component
public class AdvertMapper {

    /**
     * @param advert object
     * @return AdvertResponse DTO object
     */
    public AdvertResponse advertToAdvertResponse(Advert advert) {
        return AdvertResponse.builder()
                .title(advert.getTitle())
                .description(advert.getDescription())
                .slug(advert.getSlug())
                .price(advert.getPrice())
                .status(advert.getStatus())
                .isActive(advert.getIsActive())
                .viewCount(advert.getViewCount())
                .location(advert.getLocation())
                .build();
    }
}
