package com.project.warmyhomes.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    @NotNull(message = "Please enter title")
    @Size(max = 150, message = "Title should be at most 150 chars")
    private String title;

    @NotNull(message = "Please enter icon")
    @Size(max = 50, message = "Icon should be at most 50 chars")
    private String icon;

    @NotNull(message = "Please enter slug")
    @Size(min = 5, max = 200, message = "Slug should be at least 5 and most 200 chars")
    private String slug;
}
