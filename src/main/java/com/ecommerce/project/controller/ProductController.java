package com.ecommerce.project.controller;
import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")

//Swagger Annotation
@Tag(
		name = "Product  APIs",
	    description = "APIs for creating, retrieving, searching, updating and deleting products"
	)

public class ProductController {

    @Autowired
    ProductService productService;
    
    // Swagger Annotation
    @Operation(summary = "Add a product" , description =  "Creates a new product under the specified category.")
    @ApiResponses({
    	@ApiResponse(responseCode = "201", description = "Product created successfully"),
    	@ApiResponse(responseCode = "400", description = "Invalid product data" , content = @Content),
    	@ApiResponse(responseCode = "404", description = "Category not found" , content = @Content)
	})

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(
   
    		@Valid @RequestBody ProductDTO productDTO,
    		
    		// Swagger Annotation
    		@Parameter(description = "ID of the category for the new product", example = "1")
    		
            @PathVariable Long categoryId){
        ProductDTO savedProductDTO = productService.addProduct(categoryId, productDTO);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
    }
    
    // Swagger Annotation
    @Operation(summary = "Get all products" , description =  "Returns a paginated list of products with sorting support.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Products retrieved successfully")
	})

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
    		
    		// Swagger Annotation
    		@Parameter(description = "Page number to retrieve",example = "0")
    		
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            
            // Swagger Annotation
    		@Parameter(description = "Number of products per page",example = "10")
    		
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            
            // Swagger Annotation
    		@Parameter(description = "Product field used for sorting",example = "price")
    		
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            
            // Swagger Annotation
    		@Parameter(description = "v",example = "asc")
    		
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ){
        ProductResponse productResponse = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
    
    // Swagger Annotation
    @Operation(summary = "Get products by category" , description =  "Returns paginated products belonging to the specified category.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
    	@ApiResponse(responseCode = "404", description = "Category not found" , content = @Content)
	})

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(
    		
    		// Swagger Annotation
    		@Parameter(description = "ID of the category", example = "1")
    		
    		@PathVariable Long categoryId,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        ProductResponse productResponse = productService.searchByCategory(categoryId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    // Swagger Annotation
    @Operation(summary = "Search products by keyword" , description =  "Searches for products matching the specified keyword, with pagination and sorting.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Product search completed successfully"),
    	@ApiResponse(responseCode = "400", description = "Invalid search parameters" , content = @Content)
	})
    
    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(
    		
    		// Swagger Annotation
    		@Parameter(description = "Keyword used to search products", example = "phone")
    		
    		@PathVariable String keyword,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){
        ProductResponse productResponse = productService.searchProductByKeyword(keyword, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }
    
    // Swagger Annotation
    @Operation(summary = "Update a product" , description =  "Updates the details of an existing product using its ID.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Product updated successfully"),
    	@ApiResponse(responseCode = "400", description = "Invalid product data" , content = @Content),
    	@ApiResponse(responseCode = "404", description = "Product not found" , content = @Content)

	})

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(
    		
    		@Valid @RequestBody ProductDTO productDTO,
    		
    		// Swagger Annotation
    		@Parameter(description = "ID of the product to update", example = "1")
    		
            @PathVariable Long productId){
        ProductDTO updatedProductDTO = productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
    }
    
    // Swagger Annotation
    @Operation(summary = "Delete a product" , description =  "Deletes the product associated with the specified product ID.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Product deleted successfully"),
    	@ApiResponse(responseCode = "404", description = "Product not found" , content = @Content)

	})

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(
    		
    		 // Swagger Annotation
    		@Parameter(description = "ID of the product to delete", example = "1")
    		
    		@PathVariable Long productId){
        ProductDTO deletedProduct = productService.deleteProduct(productId);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }
    
    // Swagger Annotation
    @Operation(summary = "Update product image" , description =  "Uploads a new image for the specified product.")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Product image updated successfully"),
    	@ApiResponse(responseCode = "400", description = "Invalid image upload"),
    	@ApiResponse(responseCode = "404", description = "Product not found" , content = @Content)

	})

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage(
    		
    		// Swagger Annotation
    		@Parameter(description = "ID of the product", example = "1")
    		
    		@PathVariable Long productId,
            @RequestParam("image")MultipartFile image) throws IOException {
        ProductDTO updatedProduct = productService.updateProductImage(productId, image);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
