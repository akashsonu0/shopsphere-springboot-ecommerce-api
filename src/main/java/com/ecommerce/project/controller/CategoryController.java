package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

// Swagger Annotation
@Tag(
	    name = "Category APIs",
	    description = "APIs for creating, retrieving, updating and deleting categories"
	)

public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    // Swagger Annotation
    @Operation(summary = "Get all categories" , description = "Returns a paginated list of categories with sorting support.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Categories retrieved successfully"),
    	@ApiResponse(responseCode = "400", description = "Invalid request parameters" , content = @Content),
	})
    
    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
    		
    		// Swagger Annotation
    		@Parameter(description = "Page number to retrieve", example = "0")
    		
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            
            @Parameter(description = "Number of categories per page", example = "10")
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            
            @Parameter(description = "Field used for sorting", example = "categoryName")
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            
            @Parameter(description = "Sorting direction: asc or desc", example = "asc")
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
    	
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    // Swagger Annotation
    @Operation(summary = "Create a new category" , description = "Creates a category using the category details provided in the request body.")
    @ApiResponses({
    	@ApiResponse(responseCode = "201", description = "Category created successfully"),
    	@ApiResponse(responseCode = "400", description = "Invalid category data" , content = @Content),
    	@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
	})

    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    // Swagger Annotation
    @Operation(summary = "Delete a category" , description = "Deletes a category using its category ID.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Category deleted successfully"),
    	@ApiResponse(responseCode = "404", description = "Category not found" , content = @Content),
	})
    
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@Parameter(description = "ID of the category to delete" , example = "1")
    		@PathVariable Long categoryId){
            CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }

    // Swagger Annotation
    @Operation(summary = "Update a category" , description = "Updates an existing category using its ID and the provided category details.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Category updated successfully"),
    	@ApiResponse(responseCode = "400", description = "Invalid category data" , content = @Content),
    	@ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
	})
    
    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(
    		
    		@Valid @RequestBody CategoryDTO categoryDTO,
    		
    		// Swagger Annotation
    		@Parameter(description = "ID of the category to update",example = "1")
    		
            @PathVariable Long categoryId){
            CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
            return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }
}
