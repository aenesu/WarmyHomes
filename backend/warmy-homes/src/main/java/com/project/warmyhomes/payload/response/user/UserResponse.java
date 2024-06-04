package com.project.warmyhomes.payload.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.warmyhomes.payload.response.abstracts.BaseUserResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse extends BaseUserResponse {
}
