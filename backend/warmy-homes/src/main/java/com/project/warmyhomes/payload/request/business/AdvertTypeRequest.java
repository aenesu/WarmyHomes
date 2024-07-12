package com.project.warmyhomes.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertTypeRequest {

    @NotNull(message = "Please enter title")
    @Size(max = 30, message = "Title should be at most 30 chars")
    private String title;
}
