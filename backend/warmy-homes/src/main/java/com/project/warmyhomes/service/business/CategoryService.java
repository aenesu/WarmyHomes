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
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        } else {
            return categoryRepository.findAll(pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
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
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        } else {
            return categoryRepository.findAll(pageable)
                    .map(categoryMapper::mapCategoryToCategoryResponse);
        }
    }

    /**
     * Retrieve a category by its ID and returns it wrapped in a ResponseMessage.
     * <p>
     * This method first finds a Category entity by the given category ID. It then retrieves
     * the associated category properties and maps the Category entity to a CategoryResponse.
     * Finally, it wraps the CategoryResponse along with a success message in a ResponseMessage
     * and returns it.
     *
     * @param categoryId the ID of the category to retrieve
     * @return a ResponseMessage containing the CategoryResponse and a success message
     */
    public ResponseMessage<CategoryResponse> getCategoryById(Long categoryId) {

        Category category = methodHelper.isCategoryExistById(categoryId);

        List<CategoryPropertyResponse> categoryPropertyResponses = getCategoryPropertyKeyAndPropertyValueByCategoryId(category.getId());

        CategoryResponse categoryResponse = categoryMapper.mapCategoryToCategoryResponse(category);
        categoryResponse.setProperties(categoryPropertyResponses);

        return ResponseMessage.<CategoryResponse>builder()
                .message(SuccessMessages.CATEGORY_FOUND)
                .object(categoryResponse)
                .httpStatus(HttpStatus.OK)
                .build();
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
        Category category = categoryMapper.mapCategoryRequestToCategory(categoryRequest);
        Category createdCategory = categoryRepository.save(category);

        // Initialize the response list
        List<CategoryPropertyResponse> categoryPropertyResponses = new ArrayList<>();

        if (categoryRequest.getProperties() != null) {

            for (CategoryPropertyRequest propertyRequest : categoryRequest.getProperties()) {
                CategoryPropertyKey propertyKey = categoryMapper.mapCategoryPropertyRequestToCategoryPropertyKey(propertyRequest);
                propertyKey.setCategory(createdCategory);
                CategoryPropertyKey propertyKeyCreated = categoryPropertyKeyRepository.save(propertyKey);

                CategoryPropertyValue propertyValue = categoryMapper.mapCategoryPropertyRequestToCategoryPropertyValue(propertyRequest);
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
        CategoryResponse categoryResponse = categoryMapper.mapCategoryToCategoryResponse(createdCategory);
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
        Category category = methodHelper.isCategoryExistById(categoryId);

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
                .object(categoryMapper.mapCategoryToCategoryResponse(updatedCategory))
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
        Category category = methodHelper.isCategoryExistById(categoryId);

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
                .object(categoryMapper.mapCategoryToCategoryResponse(category))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Retrieve all property keys and values for a given category ID and return them in a ResponseEntity.
     * <p>
     * This method first finds a Category entity by the given category ID. It then retrieves
     * the associated category properties and returns them in a ResponseEntity with HTTP status OK.
     *
     * @param categoryId the ID of the category to retrieve property keys and values for
     * @return a ResponseEntity containing a list of CategoryPropertyResponse objects
     */
    public ResponseEntity<List<CategoryPropertyResponse>> getAllPropertyKeyByCategory(Long categoryId) {
        Category category = methodHelper.isCategoryExistById(categoryId);

        List<CategoryPropertyResponse> categoryPropertyResponses = getCategoryPropertyKeyAndPropertyValueByCategoryId(category.getId());

        return ResponseEntity.ok(categoryPropertyResponses);
    }

    /**
     * Retrieve the property keys and their corresponding values for a given category ID.
     * <p>
     * This method fetches all property keys associated with the given category ID and then
     * retrieves the corresponding property values for each key. It constructs a list of
     * CategoryPropertyResponse objects, each containing a key and its corresponding value,
     * and returns this list.
     *
     * @param categoryId the ID of the category to retrieve property keys and values for
     * @return a list of CategoryPropertyResponse objects containing the property keys and values
     */
    private List<CategoryPropertyResponse> getCategoryPropertyKeyAndPropertyValueByCategoryId(Long categoryId) {

        // Fetch property keys for the category
        List<CategoryPropertyKey> propertyKeys = categoryPropertyKeyRepository.getCategoryPropertyKeysByCategoryId(categoryId);

        // Initialize the list to hold CategoryPropertyResponse objects
        List<CategoryPropertyResponse> categoryPropertyResponses = new ArrayList<>();

        // Iterate over each property key and fetch the corresponding property value
        for (CategoryPropertyKey propertyKey : propertyKeys) {
            CategoryPropertyValue propertyValue = categoryPropertyValueRepository.findByPropertyKeyId(propertyKey.getId());

            // Create a response object for the current property
            CategoryPropertyResponse propertyResponse = CategoryPropertyResponse.builder()
                    .keyId(propertyKey.getId())
                    .keyName(propertyKey.getName())
                    .value(propertyValue != null ? propertyValue.getValue() : null)
                    .build();

            // Add the property response to the list
            categoryPropertyResponses.add(propertyResponse);
        }
        return categoryPropertyResponses;
    }

    /**
     * Add a new category property key and its associated value to the specified category.
     *
     * @param categoryPropertyRequest The request containing the property key and value data.
     * @param categoryId              The ID of the category to which the property key will be added.
     * @return A ResponseMessage containing the created CategoryPropertyResponse and HTTP status.
     * @throws ResourceNotFoundException if the category with the specified ID is not found.
     */
    public ResponseMessage<CategoryPropertyResponse> addCategoryPropertyKey(CategoryPropertyRequest categoryPropertyRequest, Long categoryId) {
        Category category = methodHelper.isCategoryExistById(categoryId);

        CategoryPropertyKey categoryPropertyKey = categoryMapper.mapCategoryPropertyRequestToCategoryPropertyKey(categoryPropertyRequest);
        categoryPropertyKey.setCategory(category);
        CategoryPropertyKey addedPropertyKey = categoryPropertyKeyRepository.save(categoryPropertyKey);

        CategoryPropertyValue categoryPropertyValue = categoryMapper.mapCategoryPropertyRequestToCategoryPropertyValue(categoryPropertyRequest);
        categoryPropertyValue.setPropertyKey(addedPropertyKey);
        CategoryPropertyValue addedPropertyValue = categoryPropertyValueRepository.save(categoryPropertyValue);

        return ResponseMessage.<CategoryPropertyResponse>builder()
                .message(SuccessMessages.CATEGORY_PROPERTY_CREATE)
                .object(categoryMapper.mapCategoryPropertyKeyAndPropertyValueToCategoryResponse(addedPropertyKey, addedPropertyValue))
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    /**
     * Update an existing category property key and its associated value.
     *
     * @param categoryPropertyRequest The request containing the updated property key and value data.
     * @param propertyKeyId           The ID of the property key to be updated.
     * @return A ResponseMessage containing the updated CategoryPropertyResponse and HTTP status.
     * @throws ResourceNotFoundException if the property key with the specified ID is not found.
     */
    public ResponseMessage<CategoryPropertyResponse> updateCategoryPropertyKey(CategoryPropertyRequest categoryPropertyRequest, Long propertyKeyId) {
        CategoryPropertyKey propertyKey = methodHelper.isCategoryPropertyKeyExistById(propertyKeyId);

        methodHelper.isCategoryPropertyKeyBuiltIn(propertyKey);

        CategoryPropertyValue propertyValue = categoryPropertyValueRepository.findByPropertyKeyId(propertyKey.getId());


        propertyKey.setName(categoryPropertyRequest.getKeyName());

        propertyValue.setPropertyKey(propertyKey);
        propertyValue.setValue(categoryPropertyRequest.getValue());

        propertyKey.setPropertyValues(propertyValue);

        CategoryPropertyKey updatedPropertyKey = categoryPropertyKeyRepository.save(propertyKey);
        CategoryPropertyValue updatedPropertyValue = categoryPropertyValueRepository.save(propertyValue);

        return ResponseMessage.<CategoryPropertyResponse>builder()
                .message(SuccessMessages.CATEGORY_PROPERTY_UPDATE)
                .object(categoryMapper.mapCategoryPropertyKeyAndPropertyValueToCategoryResponse(updatedPropertyKey, updatedPropertyValue))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Delete a category property key and its associated value from the database.
     *
     * @param propertyKeyId The ID of the property key to be deleted.
     * @return A ResponseMessage containing the deleted CategoryPropertyResponse and HTTP status.
     * @throws ResourceNotFoundException if the property key with the specified ID is not found.
     */
    public ResponseMessage<CategoryPropertyResponse> deleteCategoryPropertyKey(Long propertyKeyId) {

        CategoryPropertyKey propertyKey = methodHelper.isCategoryPropertyKeyExistById(propertyKeyId);

        methodHelper.isCategoryPropertyKeyBuiltIn(propertyKey);

        CategoryPropertyValue propertyValue = categoryPropertyValueRepository.findByPropertyKeyId(propertyKey.getId());

        // Delete related records in favorites and logs
        categoryPropertyValueRepository.deleteById(propertyValue.getId());

        categoryPropertyKeyRepository.deleteById(propertyKey.getId());

        return ResponseMessage.<CategoryPropertyResponse>builder()
                .message(SuccessMessages.CATEGORY_PROPERTY_DELETE)
                .object(categoryMapper.mapCategoryPropertyKeyAndPropertyValueToCategoryResponse(propertyKey, propertyValue))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Retrieve a category and its associated properties using the category's slug.
     *
     * @param slug The slug of the category to be retrieved.
     * @return A ResponseMessage containing the CategoryResponse and HTTP status.
     * @throws ResourceNotFoundException if the category with the specified slug is not found.
     */
    public ResponseMessage<CategoryResponse> getCategoryBySlug(String slug) {
        Category category = categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CATEGORY_WITH_SLUG, slug)));

        List<CategoryPropertyResponse> categoryPropertyResponses = getCategoryPropertyKeyAndPropertyValueByCategoryId(category.getId());

        CategoryResponse categoryResponse = categoryMapper.mapCategoryToCategoryResponse(category);
        categoryResponse.setProperties(categoryPropertyResponses);

        return ResponseMessage.<CategoryResponse>builder()
                .message(SuccessMessages.CATEGORY_FOUND)
                .object(categoryResponse)
                .httpStatus(HttpStatus.OK)
                .build();
    }
}