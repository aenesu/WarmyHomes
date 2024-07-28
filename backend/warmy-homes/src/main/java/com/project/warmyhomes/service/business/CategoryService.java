package com.project.warmyhomes.service.business;

import com.project.warmyhomes.entity.concretes.business.Category;
import com.project.warmyhomes.entity.concretes.business.CategoryPropertyKey;
import com.project.warmyhomes.entity.concretes.business.CategoryPropertyValue;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.mappers.CategoryMapper;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.payload.messages.SuccessMessages;
import com.project.warmyhomes.payload.request.business.CategoryPropertyRequest;
import com.project.warmyhomes.payload.request.business.CategoryRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.CategoryPropertyResponse;
import com.project.warmyhomes.payload.response.business.CategoryResponse;
import com.project.warmyhomes.repository.business.CategoryPropertyKeyRepository;
import com.project.warmyhomes.repository.business.CategoryPropertyValueRepository;
import com.project.warmyhomes.repository.business.CategoryRepository;
import com.project.warmyhomes.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;
    private final CategoryPropertyValueRepository categoryPropertyValueRepository;

    //helper
    private final PageableHelper pageableHelper;

    /**
     * Create a new category based on the provided CategoryRequest.
     * <p>
     * Steps:
     * 1. Map the CategoryRequest to a Category entity.
     * 2. Process the properties from the request:
     * - For each property, create a new CategoryPropertyKey and associate it with the Category.
     * - Create a CategoryPropertyValue for each property and associate it with the corresponding CategoryPropertyKey.
     * 3. Save the Category entity to the database.
     * 4. Build a response message containing the details of the created Category.
     * - If properties are provided, include them in the response.
     *
     * @param categoryRequest The request object containing details of the category to be created.
     * @return A ResponseMessage containing the created Category details and a success message.
     * @throws ResourceNotFoundException If a specified property key ID is not found in the database.
     */
    public ResponseMessage<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
        // Map the request to the entity and save it
        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        Category createdCategory = categoryRepository.save(category);

        // Initialize the response list
        List<CategoryPropertyResponse> categoryPropertyResponses = new ArrayList<>();

        if (categoryRequest.getProperties() != null) {

            for (CategoryPropertyRequest propertyRequest : categoryRequest.getProperties()) {
                CategoryPropertyKey propertyKey = categoryMapper.categoryPropertyRequestToCategoryPropertyKey(propertyRequest.getKeyName());
                propertyKey.setCategory(createdCategory);
                CategoryPropertyKey propertyKeyCreated = categoryPropertyKeyRepository.save(propertyKey);

                CategoryPropertyValue propertyValue = categoryMapper.categoryPropertyRequestToCategoryPropertyValue(propertyRequest.getValue());
                propertyValue.setPropertyKey(propertyKeyCreated);
                CategoryPropertyValue propertyValueCreated = categoryPropertyValueRepository.save(propertyValue);

                // Add the property to the response list
                categoryPropertyResponses.add(CategoryPropertyResponse.builder()
                        .keyId(propertyKeyCreated.getId())
                        .keyName(propertyKeyCreated.getName())
                        .value(propertyValueCreated.getValue())
                        .build());
            }
        }

        // Create the response object with or without properties
        CategoryResponse categoryResponse = categoryMapper.categoryToCategoryResponse(createdCategory);
        if (!categoryPropertyResponses.isEmpty()) {
            categoryResponse.setProperties(categoryPropertyResponses);
        }

        // Build and return the response message
        return ResponseMessage.<CategoryResponse>builder()
                .message(SuccessMessages.CATEGORY_CREATE)
                .object(categoryResponse)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    /**
     * Retrieve a paginated list of categories based on the provided parameters.
     *
     * @param query the search query to filter categories by title; if null, all categories are retrieved
     * @param page  the page number to retrieve (0-indexed)
     * @param size  the number of items per page
     * @param sort  the field by which to sort the results
     * @param type  the sort direction, either "asc" for ascending or "desc" for descending
     * @return a page of CategoryResponse objects containing the filtered and sorted categories
     */
    public Page<CategoryResponse> getCategoriesByPage(String query, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        if (query != null) {
            return categoryRepository.findCategoryByQueryActiveInTrue(query, pageable)
                    .map(categoryMapper::categoryToCategoryResponse);
        } else {
            return categoryRepository.findAll(pageable)
                    .map(categoryMapper::categoryToCategoryResponse);
        }
    }

    /**
     * Retrieve a paginated list of categories for admin users based on the provided parameters.
     *
     * @param query the search query to filter categories by title; if null, all categories are retrieved
     * @param page  the page number to retrieve (0-indexed)
     * @param size  the number of items per page
     * @param sort  the field by which to sort the results
     * @param type  the sort direction, either "asc" for ascending or "desc" for descending
     * @return a page of CategoryResponse objects containing the filtered and sorted categories
     */
    public Page<CategoryResponse> getCategoriesByPageByAdmin(String query, int page, int size, String sort, String type) {
        // Create pageable object with sorting parameters
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        if (query != null) {
            return categoryRepository.findCategoryByQuery(query, pageable)
                    .map(categoryMapper::categoryToCategoryResponse);
        } else {
            return categoryRepository.findAll(pageable)
                    .map(categoryMapper::categoryToCategoryResponse);
        }
    }

    /**
     * Retrieve a category by its ID and returns it wrapped in a ResponseMessage.
     *
     * @param categoryId the ID of the category to retrieve
     * @return a ResponseMessage containing the CategoryResponse and a success message
     */
    public ResponseMessage<CategoryResponse> getCategoryById(Long categoryId) {
        // Fetch the category by ID
        Category category = findCategoryById(categoryId);

        // Fetch property keys for the category
        List<CategoryPropertyKey> propertyKeys = categoryPropertyKeyRepository.getCategoryPropertyKeysByCategoryId(categoryId);

        // Initialize the list to hold CategoryPropertyResponse objects
        List<CategoryPropertyResponse> categoryPropertyResponses = new ArrayList<>();

        // Iterate over each property key and fetch the corresponding property value
        for (CategoryPropertyKey propertyKey : propertyKeys) {
            CategoryPropertyValue propertyValue = categoryPropertyValueRepository.findByPropertyKey(propertyKey);

            // Create a response object for the current property
            CategoryPropertyResponse propertyResponse = CategoryPropertyResponse.builder()
                    .keyId(propertyKey.getId())
                    .keyName(propertyKey.getName())
                    .value(propertyValue != null ? propertyValue.getValue() : null)
                    .build();

            // Add the property response to the list
            categoryPropertyResponses.add(propertyResponse);
        }

        // Create the response object with properties
        CategoryResponse categoryResponse = categoryMapper.categoryToCategoryResponse(category);
        categoryResponse.setProperties(categoryPropertyResponses);

        // Return the response message
        return ResponseMessage.<CategoryResponse>builder()
                .message(SuccessMessages.CATEGORY_FOUND)
                .object(categoryResponse)
                .httpStatus(HttpStatus.OK)
                .build();
    }


    /**
     * Find a category by its ID.
     *
     * @param categoryId the ID of the category to retrieve
     * @return the Category object if found
     * @throws ResourceNotFoundException if the category is not found
     */
    private Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CATEGORY, categoryId)));
    }
}
