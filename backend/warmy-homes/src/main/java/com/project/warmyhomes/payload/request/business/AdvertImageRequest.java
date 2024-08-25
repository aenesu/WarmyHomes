package com.project.warmyhomes.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertImageRequest {

    @NotNull(message = "Please enter image")
    private String data;

    @NotNull(message = "Please enter image name")
    private String name;

    @NotNull(message = "Please enter image type")
    private String type;

    @NotNull(message = "Please enter image featured status")
    private Boolean featured;
}
