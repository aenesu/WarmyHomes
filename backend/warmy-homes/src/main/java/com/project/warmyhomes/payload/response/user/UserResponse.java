package com.project.warmyhomes.payload.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.warmyhomes.entity.concretes.business.Advert;
import com.project.warmyhomes.entity.concretes.business.Favorite;
import com.project.warmyhomes.entity.concretes.business.Log;
import com.project.warmyhomes.entity.concretes.business.TourRequest;
import com.project.warmyhomes.payload.response.abstracts.BaseUserResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse extends BaseUserResponse {
    private List<Advert> adverts;
    private List<TourRequest> tourRequests;
    private List<Favorite> favorites;
    private List<Log> logs;
}
