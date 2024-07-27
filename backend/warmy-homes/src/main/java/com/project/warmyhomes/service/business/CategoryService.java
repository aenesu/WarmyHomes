package com.project.warmyhomes.service.business;

import com.project.warmyhomes.entity.concretes.business.Category;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.mappers.CategoryMapper;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.payload.messages.SuccessMessages;
import com.project.warmyhomes.payload.request.business.CategoryRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.CategoryResponse;
import com.project.warmyhomes.repository.business.CategoryRepository;
import com.project.warmyhomes.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    //helper
    private final PageableHelper pageableHelper;

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
    // TODO: Add response with properties fields
    public ResponseMessage<CategoryResponse> getCategoryById(Long categoryId) {
        return ResponseMessage.<CategoryResponse>builder()
                .message(SuccessMessages.CATEGORY_FOUND)
                .object(categoryMapper.categoryToCategoryResponse(findCategoryById(categoryId)))
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
     * Create a new category based on the provided CategoryRequest and returns the created category.
     *
     * @param categoryRequest the request object containing the details of the category to be created
     * @return the response object containing the details of the created category
     */
    // TODO: Add request body in the postman
    public CategoryResponse createContact(CategoryRequest categoryRequest) {
        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        Category createdCategory = categoryRepository.save(category);
        return categoryMapper.categoryToCategoryResponse(createdCategory);
    }
}
