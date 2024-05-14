package com.project.warmyhomes.payload.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.warmyhomes.payload.response.abstracts.BaseUserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse extends BaseUserResponse {
}
