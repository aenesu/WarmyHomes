package com.project.warmyhomes.payload.mappers;

import com.project.warmyhomes.entity.concretes.business.Advert;
import com.project.warmyhomes.entity.concretes.business.AdvertType;
import com.project.warmyhomes.payload.request.business.AdvertTypeRequest;
import com.project.warmyhomes.payload.response.business.AdvertTypeResponse;
import org.springframework.stereotype.Component;

@Component
public class AdvertTypeMapper {

    /**
     * @param advertTypeRequest DTO
     * @return advert type object
     */

    public AdvertType mapAdvertTypeRequestToAdvertType(AdvertTypeRequest advertTypeRequest) {
        return AdvertType.builder()
                .title(advertTypeRequest.getTitle())
                .builtIn(false)
                .build();
    }

    /**
     * @param advertType object
     * @return AdvertTypeResponse DTO object
     */
    public AdvertTypeResponse mapAdvertTypeToAdvertTypeResponse(AdvertType advertType) {
        return AdvertTypeResponse.builder()
                .id(advertType.getId())
                .title(advertType.getTitle())
                .build();
    }
}
