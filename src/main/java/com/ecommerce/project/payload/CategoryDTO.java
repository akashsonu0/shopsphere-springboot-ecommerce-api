package com.ecommerce.project.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Category details used to create, retrieve or update a category")
public class CategoryDTO {

    @Schema(
        description = "Unique ID of the category",
        example = "101",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long categoryId;

    @NotBlank(message = "Category name cannot be blank")
    @Size(
        min = 5,
        message = "Category name must contain at least 5 characters"
    )
    @Schema(
        description = "Name of the product category. Minimum 5 characters required.",
        example = "Smartphones",
        minLength = 5,
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String categoryName;
}