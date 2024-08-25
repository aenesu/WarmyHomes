package com.project.warmyhomes.payload.mappers;

import com.project.warmyhomes.entity.concretes.business.Category;
import com.project.warmyhomes.entity.concretes.business.CategoryPropertyKey;
import com.project.warmyhomes.entity.concretes.business.CategoryPropertyValue;
import com.project.warmyhomes.payload.request.business.CategoryPropertyRequest;
import com.project.warmyhomes.payload.request.business.CategoryRequest;
import com.project.warmyhomes.payload.response.business.CategoryPropertyResponse;
import com.project.warmyhomes.payload.response.business.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    /**
     * @param categoryRequest DTO
     * @return category from DB
     */
    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .title(categoryRequest.getTitle())
                .slug(categoryRequest.getSlug())
                .icon(categoryRequest.getIcon())
                .seq(categoryRequest.getSeq())
                .builtIn(false)
                .isActive(categoryRequest.getIsActive())
                .build();
    }

    /**
     * @param category object
     * @return CategoryResponse DTO object
     */
    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .icon(category.getIcon())
                .slug(category.getSlug())
                .build();
    }

    /**
     * @param categoryPropertyRequest DTO
     * @return categoryPropertyKey from DB
     */
    public CategoryPropertyKey mapCategoryPropertyRequestToCategoryPropertyKey(CategoryPropertyRequest categoryPropertyRequest) {
        return CategoryPropertyKey.builder()
                .name(categoryPropertyRequest.getKeyName())
                .builtIn(false)
                .build();
    }

    /**
     * @param categoryPropertyRequest DTO
     * @return categoryPropertyValue from DB
     */
    public CategoryPropertyValue mapCategoryPropertyRequestToCategoryPropertyValue(CategoryPropertyRequest categoryPropertyRequest) {
        return CategoryPropertyValue.builder()
                .value(categoryPropertyRequest.getValue())
                .build();
    }

    /**
     * @param addedPropertyKey   object
     * @param addedPropertyValue object
     * @return CategoryPropertyResponse DTO object
     */
    public CategoryPropertyResponse mapCategoryPropertyKeyAndPropertyValueToCategoryResponse(CategoryPropertyKey addedPropertyKey, CategoryPropertyValue addedPropertyValue) {
        return CategoryPropertyResponse.builder()
                .keyId(addedPropertyKey.getId())
                .keyName(addedPropertyKey.getName())
                .value(addedPropertyValue.getValue())
                .build();
    }
}

