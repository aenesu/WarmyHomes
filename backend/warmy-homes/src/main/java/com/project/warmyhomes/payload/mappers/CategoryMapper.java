package com.project.warmyhomes.payload.mappers;

import com.project.warmyhomes.entity.concretes.business.Category;
import com.project.warmyhomes.payload.request.business.CategoryRequest;
import com.project.warmyhomes.payload.response.business.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    /**
     * @param categoryRequest DTO
     * @return category from DB
     */
    public Category categoryRequestToCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .title(categoryRequest.getTitle())
                .icon(categoryRequest.getIcon())
                .slug(categoryRequest.getSlug())
                .build();
    }

    /**
     * @param category object
     * @return CategoryResponse DTO object
     */
    public CategoryResponse categoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .icon(category.getIcon())
                .slug(category.getSlug())
                .build();
    }
}
