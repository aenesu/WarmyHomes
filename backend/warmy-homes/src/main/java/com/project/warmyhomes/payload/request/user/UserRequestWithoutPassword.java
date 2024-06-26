package com.project.warmyhomes.payload.request.user;

import com.project.warmyhomes.payload.request.abstracts.AbstractUserRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserRequestWithoutPassword extends AbstractUserRequest {
}
