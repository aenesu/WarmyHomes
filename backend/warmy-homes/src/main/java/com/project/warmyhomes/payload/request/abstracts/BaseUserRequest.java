package com.project.warmyhomes.payload.request.abstracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor -> It will open when the field is added.
@NoArgsConstructor
public abstract class BaseUserRequest extends AbstractUserRequest {
}
