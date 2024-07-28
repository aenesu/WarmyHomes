package com.project.warmyhomes.payload.response.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryPropertyResponse {
    private Long keyId;
    private String keyName;
    private String value;
}


