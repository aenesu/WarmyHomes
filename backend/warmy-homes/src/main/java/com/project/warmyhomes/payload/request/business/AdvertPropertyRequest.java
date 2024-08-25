package com.project.warmyhomes.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertPropertyRequest {
    @NotNull(message = "Please enter key id")
    private Long keyId;

    @NotNull(message = "Please enter value")
    @Size(max = 100, message = "Value should be at most 100 chars")
    private String value;

}
