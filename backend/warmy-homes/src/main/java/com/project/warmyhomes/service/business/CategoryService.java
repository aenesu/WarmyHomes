package com.project.warmyhomes.service.business;

import com.project.warmyhomes.entity.concretes.business.Category;
import com.project.warmyhomes.entity.concretes.business.CategoryPropertyKey;
import com.project.warmyhomes.entity.concretes.business.CategoryPropertyValue;
import com.project.warmyhomes.exception.BadRequestException;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.mappers.CategoryMapper;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.payload.messages.SuccessMessages;
import com.project.warmyhomes.payload.request.business.CategoryPropertyRequest;
import com.project.warmyhomes.payload.request.business.CategoryRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.CategoryPropertyResponse;
import com.project.warmyhomes.payload.response.business.CategoryResponse;
import com.project.warmyhomes.repository.business.AdvertRepository;
import com.project.warmyhomes.repository.business.CategoryPropertyKeyRepository;
import com.project.warmyhomes.repository.business.CategoryPropertyValueRepository;
import com.project.warmyhomes.repository.business.CategoryRepository;
import com.project.warmyhomes.service.helper.MethodHelper;
import com.project.warmyhomes.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;
    private final CategoryPropertyValueRepository categoryPropertyValueRepository;
    private final AdvertRepository advertRepository;
    //helper
    private final PageableHelper pageableHelper;
    private final MethodHelper methodHelper;

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
            CategoryPropertyValue propertyValue = categoryPropertyValueRepository.findByPropertyKey(propertyKey.getId());

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
                CategoryPropertyKey propertyKey = categoryMapper.categoryPropertyRequestToCategoryPropertyKey(propertyRequest);
                propertyKey.setCategory(createdCategory);
                CategoryPropertyKey propertyKeyCreated = categoryPropertyKeyRepository.save(propertyKey);

                CategoryPropertyValue propertyValue = categoryMapper.categoryPropertyRequestToCategoryPropertyValue(propertyRequest);
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
     * Update an existing category with the provided details.
     *
     * @param categoryRequest the new details for the category
     * @param categoryId      the ID of the category to be updated
     * @return a ResponseMessage containing the updated category details
     */
    public ResponseMessage<CategoryResponse> updateCategory(CategoryRequest categoryRequest, Long categoryId) {
        // Find the existing category by ID
        Category category = findCategoryById(categoryId);

        // Check if the category is built-in (non-editable)
        methodHelper.isCategoryBuiltIn(category);

        // Update the category details
        category.setTitle(categoryRequest.getTitle());
        category.setSlug(categoryRequest.getSlug());
        category.setIcon(categoryRequest.getIcon());
        category.setSeq(categoryRequest.getSeq());
        category.setIsActive(categoryRequest.getIsActive());

        // Save the updated category to the repository
        Category updatedCategory = categoryRepository.save(category);

        // Return a response message with the updated category details
        return ResponseMessage.<CategoryResponse>builder()
                .message(SuccessMessages.CATEGORY_UPDATE)
                .object(categoryMapper.categoryToCategoryResponse(updatedCategory))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Delete an existing category by its ID.
     *
     * @param categoryId the ID of the category to be deleted
     * @return a ResponseMessage confirming the deletion
     * @throws BadRequestException if the category is associated with any adverts
     */
    public ResponseMessage<CategoryResponse> deleteCategory(Long categoryId) {
        // Find the existing category by ID
        Category category = findCategoryById(categoryId);

        // Check if the category is built-in (non-deletable)
        methodHelper.isCategoryBuiltIn(category);

        // Check if there are any adverts associated with the category
        if (advertRepository.existsByCategoryId(categoryId)) {
            throw new BadRequestException(ErrorMessages.BAD_REQUEST_CATEGORY_TO_ADVERT);
        }

        // Delete the category from the repository
        categoryRepository.deleteById(categoryId);

        // Return a response message confirming the deletion
        return ResponseMessage.<CategoryResponse>builder()
                .message(SuccessMessages.CATEGORY_DELETE)
                .object(categoryMapper.categoryToCategoryResponse(category))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /*
    public ResponseEntity<List<CategoryPropertyResponse>> getAllPropertyKeyByCategory(Long categoryId) {
        Category category = findCategoryById(categoryId);

        // Fetch property keys for the category
        List<CategoryPropertyKey> propertyKeys = categoryPropertyKeyRepository.findByCategoryId(categoryId);

        // Fetch property values for each property key
        List<CategoryPropertyResponse> propertyResponses = propertyKeys.stream().map(propertyKey -> {
            List<CategoryPropertyValue> propertyValues = categoryPropertyValueRepository.findByPropertyKeyId(propertyKey.getId());


            return CategoryPropertyResponse.builder()
                    .keyId(propertyKey.getId())
                    .keyName(propertyKey.getName())
                    .value(propertyValues.stream()
                            .map(CategoryPropertyValue::getValue) // Assuming CategoryPropertyValue has a getValue() method
                            .collect(Collectors.toList())
                    )
                    .build();
        }).collect(Collectors.toList());

        return ResponseEntity.ok(propertyResponses);
    }
    */
}
