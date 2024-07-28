package com.project.warmyhomes.controller.business;

import com.project.warmyhomes.payload.request.business.CategoryRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.business.CategoryResponse;
import com.project.warmyhomes.service.business.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping // http://localhost:8080/categories + POST + JSON
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public ResponseMessage<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @GetMapping // http://localhost:8080/categories?q=villa&page=0&size=10&sort=id&type=asc + GET
    public Page<CategoryResponse> getCategoriesByPage(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type
    ) {
        return categoryService.getCategoriesByPage(query, page, size, sort, type);
    }

    @GetMapping("/admin") // http://localhost:8080/categories/admin?q=villa&page=0&size=10&sort=id&type=asc + GET
    @PreAuthorize("hasAnyAuthority('Admin','Manager')")
    public Page<CategoryResponse> getCategoriesByPageByAdmin(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type
    ) {
        return categoryService.getCategoriesByPageByAdmin(query, page, size, sort, type);
    }

    @GetMapping("/{categoryId}") // http://localhost:8080/categories/:id + GET
    public ResponseMessage<CategoryResponse> getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }
}
