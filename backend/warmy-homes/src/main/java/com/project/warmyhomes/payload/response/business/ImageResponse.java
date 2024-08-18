package com.project.warmyhomes.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.warmyhomes.entity.concretes.business.Advert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ImageResponse {
    private Long imageId;
    private byte[] data;
    private String name;
    private String type;
    private Boolean featured;
    private Long advertId;
}
