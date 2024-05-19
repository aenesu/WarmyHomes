package com.project.warmyhomes.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor -> It will open when the field is added.
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactResponse {
}
