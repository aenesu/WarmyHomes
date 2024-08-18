package com.project.warmyhomes.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.warmyhomes.payload.request.business.ImageRequest;
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
    Boolean isActive;
    Integer viewCount;
    Long advertTypeId;
    Long countryId;
    Long cityId;
    Long districtId;
    List<ImageRequest> requests;
    List<AdvertPropertyResponse> propertyRequests;
    String location;
}
