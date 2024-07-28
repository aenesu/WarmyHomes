package com.project.warmyhomes.payload.request.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPropertyRequest {

 @NotNull(message = "Please enter key name")
 @Size(max = 100, message = "Key name should be at most 100 chars")
 private String keyName;

 @NotNull(message = "Please enter value")
 @Size(max = 100, message = "Value should be at most 100 chars")
 private String value;

}
