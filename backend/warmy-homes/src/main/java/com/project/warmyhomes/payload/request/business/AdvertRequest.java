package com.project.warmyhomes.payload.request.business;

import com.project.warmyhomes.entity.concretes.business.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertRequest {

    @NotNull(message = "Please enter title")
    @Size(max = 150, message = "Title should be at most 100 chars")
    String title;

    @NotNull(message = "Please enter description")
    @Size(max = 300, message = "Title should be at most 300 chars")
    String desc;

    @NotNull(message = "Please enter price")
    @DecimalMin(value = "0.00", message = "Price must be greater than or equal to 0")
    @DecimalMax(value = "99999999.99", message = "Price must be less than or equal to 99999999.99")
    @Digits(integer = 8, fraction = 2, message = "Price must have up to 8 digits before the decimal point and 2 digits after the decimal point")
    BigDecimal price;

    @NotNull(message = "Please enter advert type id")
    Long advertTypeId;

    @NotNull(message = "Please enter country id")
    Long countryId;

    @NotNull(message = "Please enter city id")
    Long cityId;

    @NotNull(message = "Please enter district id")
    Long districtId;

    @NotNull(message = "Please enter user id")
    Long userId;

    @NotNull(message = "Please enter category id")
    Long categoryId;

    List<AdvertImageRequest> images;
    List<AdvertPropertyRequest> properties;

    String location;
}
