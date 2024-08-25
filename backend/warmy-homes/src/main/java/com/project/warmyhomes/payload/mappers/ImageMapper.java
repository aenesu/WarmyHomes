package com.project.warmyhomes.payload.mappers;

import com.project.warmyhomes.entity.concretes.business.Image;
import com.project.warmyhomes.payload.response.business.ImageIdResponse;
import com.project.warmyhomes.payload.response.business.ImageResponse;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    /**
     * @param image object
     * @return ImageResponse DTO object
     */
    public ImageResponse mapImageToImageResponse(Image image) {
        return ImageResponse.builder()
                .imageId(image.getId())
                .data(image.getData())
                .name(image.getName())
                .type(image.getType())
                .featured(image.getFeatured())
                .advertId(image.getAdvert().getId())
                .build();
    }

    /**
     * @param image object
     * @return ImageIdResponse object
     */
    public ImageIdResponse mapImageToImageIdResponse(Image image) {
        return ImageIdResponse.builder()
                .imageId(image.getId())
                .build();
    }
}
